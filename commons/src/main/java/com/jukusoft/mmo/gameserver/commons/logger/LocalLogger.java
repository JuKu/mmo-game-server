package com.jukusoft.mmo.gameserver.commons.logger;

public class LocalLogger {

    public static void print (String message, boolean printEnabled) {
        if (printEnabled) {
            LocalLogger.print(message);
        }
    }

    public static void print (String message) {
        System.out.println(message);
    }

    public static void printStacktrace (Throwable e) {
        e.printStackTrace();
    }

}
