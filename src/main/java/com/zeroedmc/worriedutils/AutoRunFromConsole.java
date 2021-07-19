package com.zeroedmc.worriedutils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.security.CodeSource;
import java.util.Map;
import java.util.Properties;

final public class AutoRunFromConsole {


    final private static String FAILMESSAGE_TITLE = "Please run in console.";
    final private static String FAILMESSAGE_BODY = "This application must be run in the console (or \"command box\").\n\nIn there, you have to type:\n\njava -jar WorriedUtils.jar";


    private static void showFailMessageAndExit() {

        JOptionPane.showMessageDialog(null, FAILMESSAGE_BODY, FAILMESSAGE_TITLE, JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }


    private enum OSType {
        UNDETERMINED, WINDOWS, LINUX, MACOS
    }


    private static OSType getOsType() {

        //        final String osName = System.getProperty("os.name");
        //        final String osVersion = System.getProperty("os.version");
        //        final String osArchitecture = System.getProperty("os.arch");
        //        System.out.println("\n\nOSNAME: " + osName);
        //        System.out.println("\n\nOSVERSION: " + osVersion);
        //        System.out.println("\n\nOSARCHITECTURE: " + osArchitecture);

        final String osName = System.getProperty("os.name", "").toLowerCase();
        if (osName.startsWith("windows")) {
            return OSType.WINDOWS;
        } else if (osName.startsWith("linux")) {
            return OSType.LINUX;
        } else if (osName.startsWith("mac os") || osName.startsWith("macos") || osName.startsWith("darwin")) {
            return OSType.MACOS;
        }

        return OSType.UNDETERMINED;
    }

    public static void runYourselfInConsole(final boolean stayOpenAfterEnd) {

        runYourselfInConsole(false, stayOpenAfterEnd, null, null);
    }

    public static void runYourselfInConsole(final String[] psvmArguments, final String fallbackExecutableName, final boolean stayOpenAfterEnd) {

        runYourselfInConsole(true, stayOpenAfterEnd, psvmArguments, fallbackExecutableName);
    }

    private static void runYourselfInConsole(final boolean useSaferApproach, final boolean stayOpenAfterEnd, final String[] psvmArguments, final String fallbackExecutableName) {

        String executableName = getExecutableName(fallbackExecutableName);

        if (useSaferApproach) {
            if (isRunFromIDE(psvmArguments)) {
                return;
            }
        } else {
            if (executableName == null) {
                // Running from IDE.
                return;
            }
        }

        if (isRunningInConsole()) {
            return;
        }

        if (executableName == null) {
            showFailMessageAndExit();
        }

        startExecutableInConsole(executableName, stayOpenAfterEnd);

        System.exit(0);
    }

    private static void startExecutableInConsole(final String executableName, final boolean stayOpenAfterEnd) {

        String launchString = null;

        switch (getOsType()) {
            case UNDETERMINED:
                break;
            case WINDOWS:
                if (stayOpenAfterEnd) {
                    launchString = "cmd /c start cmd /k java -jar \"" + executableName+"\""; // No, using /k directly here DOES NOT do the trick.
                } else {
                    launchString = "cmd /c start java -jar \"" + executableName+"\"";
                }
                break;
            case LINUX:
                break;
            case MACOS:
                break;
        }

        if (launchString == null) {
            showFailMessageAndExit();
        }

        try {
            Runtime.getRuntime().exec(launchString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isRunFromIDE(final String[] args) {

        return args != null && args.length > 0 && args[0].equalsIgnoreCase("ide");
    }

    private static boolean isRunningInConsole() {

        return System.console() != null;
    }

    public static String getExecutableName(final String fallbackExecutableName) {

        // APPROACH 1 - THE ONE EVERYBODY ON STACKOVERFLOW IS REPEATING
        String executableNameFromClass = null;
        final CodeSource codeSource = AutoRunFromConsole.class.getProtectionDomain().getCodeSource();
        if (codeSource == null) {
            System.err.println("UNEXPECTED: WorriedUtils.class.getProtectionDomain().getCodeSource() returned null");
        } else {
            final String path = codeSource.getLocation().getPath();
            if (path == null || path.isEmpty()) {
                System.err.println("UNEXPECTED: codeSource.getLocation().getPath() returned null or empty");
            } else {

                executableNameFromClass = new File(path).getName();

            }
        }


        // APPROACH 2 - QUERY SYSTEM PROPERTIES
        final Properties properties = System.getProperties();
        final String executableNameFromJavaClassPathProperty = properties.getProperty("java.class.path");
        final String executableNameFromSunJavaCommandProperty = properties.getProperty("sun.java.command");

        if (isThisProbablyTheExecutable(executableNameFromClass)) {
            return executableNameFromClass;
        }

        if (isThisProbablyTheExecutable(executableNameFromJavaClassPathProperty)) {
            return executableNameFromJavaClassPathProperty;
        }

        if (isThisProbablyTheExecutable(executableNameFromSunJavaCommandProperty)) {
            return executableNameFromSunJavaCommandProperty;
        }

        if (isThisProbablyTheExecutable(fallbackExecutableName)) {
            return fallbackExecutableName;
        }

        return null;
    }

    private static boolean isThisProbablyTheExecutable(final String candidateName) {

        if (candidateName == null || !candidateName.toLowerCase().endsWith(".jar")) {
            return false;
        }

        final File file = new File(candidateName);
        return file.exists() && file.isFile();
    }


    public static void main(final String[] args) {

        AutoRunFromConsole.runYourselfInConsole(true);
        printEnvironmentInfo();
    }

    public static void printEnvironmentInfo() {


        System.out.println("\n\n\n\n-------------------------- System.getProperties() --------------------------");
        final Properties properties = System.getProperties();
        for (final Map.Entry<Object, Object> entry : properties.entrySet()) {
            System.out.println(entry);
        }

        System.out.println("\n\n\n\n----------------------------- System.getenv() ------------------------------");
        final Map<String, String> env = System.getenv();
        for (final Map.Entry<String, String> entry : env.entrySet()) {
            System.out.println(entry);
        }

        System.out.print("\n\n\n\n");
    }


}