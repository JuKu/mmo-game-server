package com.jukusoft.mmo.gameserver.core;

import com.jukusoft.mmo.gameserver.commons.version.Version;
import com.jukusoft.mmo.gameserver.core.utils.Utils;

public class CoreInfo {

    protected CoreInfo () {
        //
    }

    public static void printStartUpInfo (Class<?> cls) {
        //load version
        Version version = new Version(cls);

        System.out.println("/***************************************************************");
        System.out.println("*");
        System.out.println("*  MMO Game Server");
        System.out.println("*  ----------------");
        System.out.println("*");
        System.out.println("*  Version: " + version.getVersion());
        System.out.println("*  Build: " + version.getRevision());
        System.out.println("*");
        System.out.println("*  Build JDK: " + version.getBuildJdk());
        System.out.println("*  Build time: " + version.getBuildTime());
        System.out.println("*  Vendor ID: " + (!version.getVendor().equals("n/a") ? version.getVendor() : version.getVendorID()));
        System.out.println("*");
        System.out.println("***************************************************************/");
    }

}
