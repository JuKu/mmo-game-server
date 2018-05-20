package com.jukusoft.mmo.gameserver.core.config;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CacheConfigTest {

    @Test
    public void testConstructor () {
        new CacheConfig();
    }

    @Test (expected = NullPointerException.class)
    public void testLoadNullFile () throws IOException {
        CacheConfig config = new CacheConfig();

        config.load(null);
    }

    @Test (expected = FileNotFoundException.class)
    public void testLoadNotExistentFile () throws IOException {
        CacheConfig config = new CacheConfig();

        config.load(new File("not-existent-file.cfg"));
    }

    @Test
    public void testLoad () throws IOException {
        CacheConfig config = new CacheConfig();

        config.load(new File("../config/cache.cfg"));
    }

}
