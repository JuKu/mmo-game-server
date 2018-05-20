package com.jukusoft.mmo.gameserver.core.config;

import com.carrotsearch.hppc.IntObjectHashMap;
import com.carrotsearch.hppc.IntObjectMap;
import com.jukusoft.mmo.gameserver.commons.config.ConfigLoader;
import com.jukusoft.mmo.gameserver.commons.logger.LocalLogger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Config {

    public static final String CONFIG_DIR = "./config/";

    //list with file names of all loaded configuration files
    protected static List<String> loadedConfigs = new ArrayList<>();

    protected static final IntObjectMap<Object> configMap = new IntObjectHashMap<>();

    public static <T extends ConfigLoader> T get (Class<T> cls) {
        Object obj = configMap.get(cls.getCanonicalName().hashCode());

        if (obj == null) {
            return null;
        }

        return cls.cast(obj);
    }

    /**
    * load all configuration files from dir
     *
     * @param dir config fir
    */
    public static void load (File dir, boolean printEnabled) {
        for (File f : dir.listFiles()) {
            //check, if file ends with .cfg and not with .example.cfg
            if (f.getAbsolutePath().endsWith(".cfg")) {
                if (f.getAbsolutePath().contains(".example")) {
                    //skip example config
                    System.out.println("skip " + f.getName() + " (example config)");

                    continue;
                }

                //check, if config was already loaded
                if (loadedConfigs.contains(f.getName())) {
                    continue;
                }

                //load config file
                System.out.println("load " + f.getName() + "");
                Config.loadFile(f);
            }
        }
    }

    public static void loadFile (File file) {
        //TODO: add code here

        loadedConfigs.add(file.getName());
    }

    public static boolean isLoaded (String fileName) {
        return loadedConfigs.contains(fileName);
    }

    public static <T extends ConfigLoader> void addLoader (T loader, Class<T> cls, String configPath) {
        LocalLogger.print("add loader " + cls.getSimpleName());

        //check, if config file exists
        File f = new File(CONFIG_DIR + configPath);

        if (!f.exists()) {
            throw new IllegalStateException("config file doesnt exists: " + configPath);
        }

        //load config
        try {
            loader.load(f);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        //add config instance to map
        configMap.put(cls.getCanonicalName().hashCode(), loader);

        loadedConfigs.add(configPath);
    }

}
