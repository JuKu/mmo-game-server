package com.jukusoft.mmo.gameserver.main;

import com.hazelcast.config.CacheSimpleConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.jukusoft.mmo.gameserver.commons.logger.LocalLogger;
import com.jukusoft.mmo.gameserver.commons.utils.FileUtils;
import com.jukusoft.mmo.gameserver.commons.version.Version;
import com.jukusoft.mmo.gameserver.core.CoreInfo;
import com.jukusoft.mmo.gameserver.commons.utils.Utils;
import com.jukusoft.mmo.gameserver.core.config.Config;
import com.jukusoft.mmo.gameserver.core.config.MySQLConfig;
import com.jukusoft.mmo.gameserver.core.config.CacheConfig;
import com.jukusoft.mmo.gameserver.database.DatabaseUpgrader;
import com.jukusoft.mmo.gameserver.main.vertx.VertxManager;
import io.vertx.core.Vertx;

import java.io.File;

public class ServerMain {

    public static final long STARTUP_TIME = System.currentTimeMillis();

    public static void main (String[] args) {
        //check, if server is running under root permissions
        if (Utils.isRootUser()) {
            System.out.println("WARNING! WARNING! WARNING!");
            System.out.println("Its not allowed to execute this gameserver under root permissions!");
            System.exit(-1);
        }

        //print startup information with version and so on
        CoreInfo.printStartUpInfo(ServerMain.class);

        Utils.printSection("Version Information");
        System.out.println("Core version: " + new Version(CoreInfo.class).getFullVersion());
        System.out.println("Commons version: " + new Version(Version.class).getFullVersion());
        System.out.println("game server version: " + new Version(ServerMain.class).getFullVersion());

        Utils.printSection("Configuration");

        //add config loader
        Config.addLoader(new MySQLConfig(), MySQLConfig.class, "mysql.cfg");
        Config.addLoader(new CacheConfig(), CacheConfig.class, "cache.cfg");

        //load config and print every confif file
        Config.load(new File("./config"), true);

        Utils.printSection("Upgrade Database");

        //create or upgrade database schema
        DatabaseUpgrader databaseUpgrader = new DatabaseUpgrader(Config.get(MySQLConfig.class));
        databaseUpgrader.migrate();
        System.out.println(databaseUpgrader.getInfo());

        Utils.printSection("Database Connection");

        //TODO: connect to database

        Utils.printSection("Cache");

        LocalLogger.print("Check, if cache directory is writable...");

        //create cache directory, if absent and check, if directory is writable
        FileUtils.createWritableDirIfAbsent(CacheConfig.CACHE_PATH);

        Utils.printSection("Hazelcast");

        //create new hazelcast instance
        log("Create hazelcast instance...");
        HazelcastInstance hazelcastInstance = createHazelcastInstance();

        Utils.printSection("Vertx");

        //create new vert.x instance
        log("Create vertx.io instance...");
        VertxManager vertxManager = new VertxManager();
        vertxManager.init(hazelcastInstance);

        //get vertx instance
        Vertx vertx = vertxManager.getVertx();
    }

    protected static void log (String msg) {
        System.out.println(msg);
    }

    public static HazelcastInstance createHazelcastInstance () {
        //create an new hazelcast instance
        com.hazelcast.config.Config config = new com.hazelcast.config.Config();

        //disable hazelcast logging
        config.setProperty("hazelcast.logging.type", "none");

        CacheSimpleConfig cacheConfig = new CacheSimpleConfig();
        config.getCacheConfigs().put("session-cache", cacheConfig);

        return Hazelcast.newHazelcastInstance(config);
    }

}
