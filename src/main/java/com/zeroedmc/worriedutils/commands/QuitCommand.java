package com.zeroedmc.worriedutils.commands;

public class QuitCommand {

    public static Boolean quit = false;
    public static void executeCommand(String[] args) throws Exception {
        System.out.println("Shutting down...");
        Thread.sleep(0, 1);
        quit = true;
    }
}
