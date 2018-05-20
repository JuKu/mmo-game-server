package com.jukusoft.mmo.gameserver.database;

import com.jukusoft.mmo.gameserver.core.config.MySQLConfig;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseUpgraderTest {

    @Test
    public void testConstructor () {
        MySQLConfig mySQLConfig = new MySQLConfig();
        new DatabaseUpgrader(mySQLConfig);
    }

    @Test
    public void testConstructor1 () {
        MySQLConfig mySQLConfig = new MySQLConfig();
        mySQLConfig.setPrefix("");
        new DatabaseUpgrader(mySQLConfig);
    }

    @Test
    public void testConnect () throws IOException {
        //https://docs.travis-ci.com/user/database-setup/#MySQL

        MySQLConfig mySQLConfig = createConfig();

        DatabaseUpgrader databaseUpgrader = new DatabaseUpgrader(mySQLConfig);
    }

    protected MySQLConfig createConfig () throws IOException {
        MySQLConfig mySQLConfig = new MySQLConfig();

        //https://docs.travis-ci.com/user/database-setup/#MySQL

        if (new File("../config/mysql.cfg").exists()) {
            mySQLConfig.load(new File("../config/mysql.cfg"));
        } else {
            mySQLConfig.load(new File("../config/tests/travis.mysql.cfg"));
        }

        return mySQLConfig;
    }

    @Test
    public void testGetInfo () throws IOException {
        MySQLConfig mySQLConfig = createConfig();
        DatabaseUpgrader databaseUpgrader = new DatabaseUpgrader(mySQLConfig);

        assertNotNull(databaseUpgrader.getInfo());
    }

    @Test
    public void testMigrate () throws IOException {
        MySQLConfig mySQLConfig = createConfig();

        DatabaseUpgrader databaseUpgrader = new DatabaseUpgrader(mySQLConfig);
        databaseUpgrader.migrate();

    }

}
