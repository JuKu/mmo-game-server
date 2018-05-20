package com.jukusoft.mmo.gameserver.main;

import com.hazelcast.config.CacheSimpleConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.jukusoft.mmo.gameserver.commons.utils.JarUtils;
import com.jukusoft.mmo.gameserver.core.utils.Utils;
import com.jukusoft.mmo.gameserver.main.vertx.VertxManager;
import io.vertx.core.Vertx;

import java.net.URISyntaxException;

public class ServerMain {

    public static void main (String[] args) {
        System.out.println("======== Gameserver ========");

        Utils.printSection("Version Information");

        //TODO: print version information

        //System.out.println("jar path: " + JarUtils.getJarFileOfClass(ServerMain.class).getAbsolutePath());

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
