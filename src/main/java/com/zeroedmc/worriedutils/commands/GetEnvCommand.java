package com.zeroedmc.worriedutils.commands;

public class GetEnvCommand {
    public static void executeCommand(String[] args) throws Exception {
        if(args.length == 2) {
            if(System.getenv(args[1]) != null) {
                System.out.println("System environment variable " + args[1] + " has value " + System.getenv(args[1]));
            } else if(System.getenv(args[1]) == null) {
                System.out.println("ERROR: System environment variable " + args[1] + " does not exist.");
            }
        } else if(args.length == 1) {
            System.out.println("ERROR: Not enough arguments.");
        } else {
            System.out.println("ERROR: Too many arguments.");
        }
    }
}
