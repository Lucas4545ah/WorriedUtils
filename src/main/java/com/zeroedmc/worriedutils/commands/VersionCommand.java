package com.zeroedmc.worriedutils.commands;

import com.zeroedmc.worriedutils.WorriedUtils;

public class VersionCommand {
    public static void executeCommand(String[] args) throws Exception {
        if(args.length == 1) {
            System.out.println("Currently running WorriedUtils version " + WorriedUtils.versionType + " " + WorriedUtils.version);
        } else if(args.length > 1) {
            System.out.println("ERROR: Not enough arguments.");
        } else {
            System.out.println("ERROR: Too many arguments.");
        }
    }
}

