package com.zeroedmc.worriedutils;

import com.zeroedmc.worriedutils.commands.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WorriedUtils {

    public static String versionType = "BETA";
    public static Double version = 0.2;

    private static File logFile;
    public static void createLogFile() {
        logFile = new File("~/WorriedUtils/log.txt");
        if(logFile.exists()) {
            return;
        }
        if(!(logFile.exists())) {
            new File("~/WorriedUtils").mkdir();
            try {
                logFile.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {

        // Automatically run from console.
        AutoRunFromConsole.runYourselfInConsole(false);

        createLogFile();

        Logger.log(Logger.LoggerLevel.INFO, "Started WorriedUtils build b02_x64 successfully.");

        System.out.println(" ");
        System.out.println("Welcome to WorriedUtils!");
        System.out.println(" ");
        System.out.println("NOTE: The software is still in beta; if you notice any bugs, please report them on the GitHub page.");
        System.out.println(" ");
        System.out.println("Currently using code designed by Dreamspace President on Stack Overflow");
        System.out.println("(https://stackoverflow.com/users/3500521/dreamspace-president)");
        System.out.println(" ");
        System.out.println("--");
        System.out.println(" ");

        while(!QuitCommand.quit) {
            Scanner scanner;
            scanner = new Scanner(System.in);

            System.out.print("WorriedUtils" + versionType + " " + version + " > ");
            String command = scanner.nextLine();

            checkCmd(command);
        }
    }

    public static void checkCmd(String command) throws Exception {
        String[] args = null;
        if(command.contains("  ")) {

        } else if(command.equalsIgnoreCase(" ")) {

        } else {
            args = command.split(" ");
            if(args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("ver")) {
                VersionCommand.executeCommand(args);
            } else if(args[0].equalsIgnoreCase("quit") || args[0].equalsIgnoreCase("exit")) {
                QuitCommand.executeCommand(args);
            } else if(args[0].equalsIgnoreCase("open") || args[0].equalsIgnoreCase("file")) {
                OpenCommand.executeCommand(args);
            } else if(args[0].equalsIgnoreCase("getenv") || args[0].equalsIgnoreCase("env")) {
                GetEnvCommand.executeCommand(args);
            } else if(args[0].equalsIgnoreCase("help")) {
                HelpCommand.executeCommand(args);
            } else if(command.isEmpty()) {

            } else {
                System.out.println("Unknown command. Use the 'help' command for a list of all commands.");
            }
        }
    }
}
