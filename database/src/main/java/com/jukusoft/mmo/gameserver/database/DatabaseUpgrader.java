package com.jukusoft.mmo.gameserver.database;

import com.jukusoft.mmo.gameserver.core.config.MySQLConfig;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.MigrationInfoService;

import java.util.HashMap;
import java.util.Map;

public class DatabaseUpgrader {

    protected Flyway flyway = null;

    public DatabaseUpgrader(MySQLConfig mySQLConfig) {
        //create the Flyway instance
        this.flyway = new Flyway();

        //https://github.com/timander/flyway-example

        //https://scalified.com/2018/01/17/java-backend-database-migration-flyway/

        //https://github.com/timander/flyway-example/blob/master/flyway.conf

        //https://www.programcreek.com/java-api-examples/index.php?api=com.googlecode.flyway.core.Flyway

        //http://www.liquibase.org/

        //https://flywaydb.org/documentation/migrations

        this.flyway.setDataSource("jdbc:mysql://" + mySQLConfig.getHost() + ":" + mySQLConfig.getPort() + "/" + mySQLConfig.getDatabase() + "?autoreconnect=true&serverTimezone=UTC&zeroDateTimeBehavior=convertToNull", mySQLConfig.getUser(), mySQLConfig.getPassword());

        Map<String,String> placeholderMap = new HashMap<>();
        placeholderMap.put("prefix", "");

        //set table prefix
        if (!mySQLConfig.getPrefix().isEmpty()) {
            placeholderMap.put("prefix", mySQLConfig.getPrefix());
        }

        this.flyway.setPlaceholders(placeholderMap);

        //set encoding
        this.flyway.setEncoding("utf-8");
    }

    public void migrate () {
        //create or upgrade database schema
        this.flyway.migrate();

        this.flyway.validate();
    }

    public String getInfo () {
        MigrationInfoService infoService = this.flyway.info();

        String s = "";

        for (MigrationInfo info : infoService.all()) {
            s += " - " + info.getDescription() + ", script: " + info.getScript() + ", state: " + info.getState() + ", version: " + info.getVersion() + "\n";
        }

        return s;
    }

}
