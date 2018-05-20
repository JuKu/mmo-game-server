package com.jukusoft.mmo.gameserver.database;

import com.jukusoft.mmo.gameserver.core.config.MySQLConfig;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class DatabaseTest {

    @Test
    public void testConstructor () {
        new Database();
    }

    @Test
    public void testInit () throws IOException, SQLException {
        //load test mysql configuration
        MySQLConfig mySQLConfig = createConfig();

        //initialize database
        Database.init(mySQLConfig);

        assertNotNull(Database.getDataSource());
        assertNotNull(Database.getConnection());

        //close database connection
        Database.close();
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

}
