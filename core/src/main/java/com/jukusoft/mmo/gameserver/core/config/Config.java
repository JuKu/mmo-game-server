package com.jukusoft.mmo.gameserver.core.config;

import com.carrotsearch.hppc.IntObjectHashMap;
import com.carrotsearch.hppc.IntObjectMap;
import com.jukusoft.mmo.gameserver.commons.config.ConfigLoader;
import com.jukusoft.mmo.gameserver.commons.config.Section;
import com.jukusoft.mmo.gameserver.commons.config.WritableSection;
import com.jukusoft.mmo.gameserver.commons.logger.LocalLogger;
import org.ini4j.Ini;
import org.ini4j.Profile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Config {

    public static final String CONFIG_DIR = "./config/";

    //list with file names of all loaded configuration files
    protected static List<String> loadedConfigs = new ArrayList<>();

    protected static final IntObjectMap<Object> configMap = new IntObjectHashMap<>();

    protected static final IntObjectMap<Section> sectionMap = new IntObjectHashMap<>();

    public static <T extends ConfigLoader> T get (Class<T> cls) {
        Object obj = configMap.get(cls.getCanonicalName().hashCode());

        if (obj == null) {
            return null;
        }

        return cls.cast(obj);
    }

    public static Section getSection (final String key) {
        return sectionMap.get(key.hashCode());
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

                try {
                    Config.loadFile(f);
                } catch (IOException e) {
                    LocalLogger.printStacktrace(e);
                    System.exit(-1);
                }
            }
        }
    }

    public static void loadFile (File file) throws IOException {
        final Ini ini = new Ini(file);

        //get all sections
        for (Map.Entry<String, Profile.Section> entry : ini.entrySet()) {
            LocalLogger.print("Found config section: " + entry.getKey());

            //load section
            final Profile.Section section = entry.getValue();

            //create new section
            final WritableSection section1 = new WritableSection();

            for (Map.Entry<String,String> entry1 : section.entrySet()) {
                section1.put(entry1.getKey(), entry1.getValue());
            }

            sectionMap.put(entry.getKey().hashCode(), section1);
        }

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
            LocalLogger.printStacktrace(e);
            System.exit(-1);
        }

        //add config instance to map
        configMap.put(cls.getCanonicalName().hashCode(), loader);

        loadedConfigs.add(configPath);
    }

}
