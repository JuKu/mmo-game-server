package com.jukusoft.mmo.gameserver.database;

import com.jukusoft.mmo.gameserver.commons.logger.LocalLogger;
import com.jukusoft.mmo.gameserver.core.config.MySQLConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {

    protected static HikariDataSource dataSource = null;

    public static void init (MySQLConfig mySQLConfig) {
        LocalLogger.print("connect to mysql database: " + mySQLConfig.getJDBCUrl());

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(mySQLConfig.getJDBCUrl());
        config.setUsername(mySQLConfig.getUser());
        config.setPassword(mySQLConfig.getPassword());

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "" + mySQLConfig.getPrepStmtCacheSize());
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "" + mySQLConfig.getPrepStmtCacheSqlLimit());

        config.addDataSourceProperty("useServerPrepStmts", "true");//Newer versions of MySQL support server-side prepared statements, this can provide a substantial performance boost. Set this property to true.

        //recommended default configuration, see https://github.com/brettwooldridge/HikariCP/wiki/MySQL-Configuration
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");

        dataSource = new HikariDataSource(config);

        LocalLogger.print("Connection established.");
    }

    public static HikariDataSource getDataSource () {
        return dataSource;
    }

    public static Connection getConnection () throws SQLException {
        return dataSource.getConnection();
    }

}
