package com.zeroedmc.worriedutils.commands;

import java.awt.*;
import java.io.File;
import java.util.Arrays;

public class OpenCommand {
    public static void executeCommand(String[] args) throws Exception {
        if(args.length >= 2) {
            if(args[1].equalsIgnoreCase("C:/") || args[1].equalsIgnoreCase("C:")) {
                File file = new File("C:/");
                try {
                    if (!Desktop.isDesktopSupported()) {
                        System.out.println("ERROR: That desktop is not supported!");
                        return;
                    }
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(file);
                    System.out.println("Opened file/folder at path " + args[1]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                for(int i = 0; i < args.length; i++) {
                    if(i > 0) {
                        stringBuilder.append(args[i]).append(" ");
                    }
                }
                File file = new File(stringBuilder.toString());
                if (!file.exists()) {
                    System.out.println("ERROR: The file at path " + stringBuilder + "does not exist.");
                } else {
                    try {
                        if(!Desktop.isDesktopSupported()) {
                            System.out.println("ERROR: That desktop is not supported!");
                            return;
                        }
                        Desktop desktop = Desktop.getDesktop();
                        desktop.open(file);
                        System.out.println("Opened file/folder at path " + stringBuilder);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
