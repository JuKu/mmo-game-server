package com.jukusoft.mmo.gameserver.main;

import com.hazelcast.config.CacheSimpleConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.jukusoft.mmo.gameserver.commons.version.Version;
import com.jukusoft.mmo.gameserver.core.CoreInfo;
import com.jukusoft.mmo.gameserver.commons.utils.Utils;
import com.jukusoft.mmo.gameserver.main.vertx.VertxManager;
import io.vertx.core.Vertx;

public class ServerMain {

    public static long startTime = 0;

    public static void main (String[] args) {
        //check, if server is running under root permissions
        if (Utils.isRootUser()) {
            System.out.println("WARNING! WARNING! WARNING!");
            System.out.println("Its not allowed to execute this gameserver under root permissions!");
            System.exit(-1);
        }

        //print startup information with version and so on
        CoreInfo.printStartUpInfo(ServerMain.class);

        //set startup time
        ServerMain.startTime = System.currentTimeMillis();

        Utils.printSection("Version Information");
        System.out.println("Core version: " + new Version(CoreInfo.class).getFullVersion());
        System.out.println("Commons version: " + new Version(Version.class).getFullVersion());
        System.out.println("game server version: " + new Version(ServerMain.class).getFullVersion());

        Utils.printSection("Configuration");

        //TODO: load configs

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
        Config config = new Config();

        //disable hazelcast logging
        config.setProperty("hazelcast.logging.type", "none");

        CacheSimpleConfig cacheConfig = new CacheSimpleConfig();
        config.getCacheConfigs().put("session-cache", cacheConfig);

        return Hazelcast.newHazelcastInstance(config);
    }

}
