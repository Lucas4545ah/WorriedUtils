package com.zeroedmc.worriedutils;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    public static final String logPath = "C:\\WorriedUtils\\log.txt";
    public static FileWriter writer;

    static {
        try {
            writer = new FileWriter(logPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Logger() throws IOException {
    }

    public static void log(LoggerLevel level, String message) throws IOException {
        if(level == LoggerLevel.INFO) {
            writer.write("[INFO] " + message + "\n");
            writer.flush();
        } else if(level == LoggerLevel.ERROR) {
            writer.write("[ERROR] " + message + "\n");
            writer.flush();
        } else if(level == LoggerLevel.WARN) {
            writer.write("[WARN] " + message + "\n");
            writer.flush();
        } else {
            writer.write("[UNDEFINED_LOG_LEVEL] " + message + "\n");
            writer.flush();
        }

    }

    public enum LoggerLevel {
        INFO, ERROR, WARN
    }
}
