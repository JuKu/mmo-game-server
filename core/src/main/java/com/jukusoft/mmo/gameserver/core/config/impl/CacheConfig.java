package com.jukusoft.mmo.gameserver.core.config.impl;

import com.jukusoft.mmo.gameserver.commons.config.ConfigLoader;
import org.ini4j.Ini;
import org.ini4j.Profile;

import java.io.File;
import java.io.IOException;

public class CacheConfig implements ConfigLoader {

    //should end with slash /
    public static String CACHE_PATH = "./cache/";

    @Override
    public void load(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("file cannot be null.");
        }

        if (!file.exists()) {
            throw new IllegalStateException("mysql config file doesnt exists: " + file.getAbsolutePath());
        }

        Ini ini = new Ini(file);
        Profile.Section section = ini.get("Cache");

        CACHE_PATH = section.getOrDefault("path", "./cache/");
    }

}
