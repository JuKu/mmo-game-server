package com.jukusoft.mmo.gameserver.core.config;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MySQLConfigTest {

    @Test
    public void testContructor () {
        new MySQLConfig();
    }

    @Test (expected = NullPointerException.class)
    public void testLoadNullFile () throws IOException {
        new MySQLConfig().load(null);
    }

    @Test (expected = IllegalStateException.class)
    public void testLoadNotExistentFile () throws IOException {
        new MySQLConfig().load(new File("not-existent-file.cfg"));
    }

    @Test
    public void testLoad () throws IOException {
        MySQLConfig mySQLConfig = new MySQLConfig();
        mySQLConfig.load(new File("../config/mysql.example.cfg"));

        assertEquals("localhost", mySQLConfig.getHost());
        assertEquals(3306, mySQLConfig.getPort());
        assertEquals("db1", mySQLConfig.getDatabase());
        assertEquals("root", mySQLConfig.getUser());
        assertEquals("testpass", mySQLConfig.getPassword());
        assertEquals("mmo_", mySQLConfig.getPrefix());

        assertEquals(30, mySQLConfig.getMaxPoolSize());
    }

    @Test
    public void testGetAndSetPrefix () {
        MySQLConfig mySQLConfig = new MySQLConfig();

        assertEquals("", mySQLConfig.getPrefix());

        mySQLConfig.setPrefix("mmo_");
        assertEquals("mmo_", mySQLConfig.getPrefix());
    }

}
