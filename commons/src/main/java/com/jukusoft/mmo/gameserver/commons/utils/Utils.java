package com.jukusoft.mmo.gameserver.commons.utils;

public class Utils {

    protected Utils() {
        //
    }

    public static String getSection (final String section) {
        if (section == null) {
            throw new NullPointerException("section cannot be null.");
        }

        if (section.isEmpty()) {
            throw new IllegalArgumentException("section cannot be empty.");
        }

        String s = "";

        s = "===={ " + section + " }";

        while (s.length() < 80) {
            s = s + "-";
        }

        return s;
    }

    public static void printSection (final String section) {
        String s = getSection(section);
        System.out.println("\n" + s);
    }

    public static boolean isRootUser () {
        return System.getProperty("user.name").equals("root") && System.getProperty("user.home").equals("/root");
    }

}
