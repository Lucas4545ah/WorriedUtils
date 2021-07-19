package com.zeroedmc.worriedutils.commands;

public class HelpCommand {
    public static void executeCommand(String[] args) throws Exception {
        System.out.println("-- Help | WorriedUtils --");
        System.out.println("  >  getenv/env: Gets the specified system environment variable's value. (args: 1)");
        System.out.println("  >  help: Shows this help message.");
        System.out.println("  >  open/file: Opens the specified file or folder in File Explorer. (args: 1)");
        System.out.println("  >  quit/exit: Quits the program.");
        System.out.println("  >  version/ver: Prints the program's version.");
    }
}
