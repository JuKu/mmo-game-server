package com.jukusoft.mmo.gameserver.frontend;

import com.jukusoft.mmo.gameserver.commons.logger.LocalLogger;
import com.jukusoft.mmo.gameserver.core.config.Config;
import com.jukusoft.mmo.gameserver.core.server.IGameServer;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

import java.util.ArrayList;
import java.util.List;

public class TCPFrontend {

    /**
    * instance of clustered vertx
    */
    protected final Vertx vertx;

    /**
    * instance of game server
    */
    protected final IGameServer gameServer;

    /**
    * list with all active servers
    */
    protected final List<NetServer> servers = new ArrayList<>();

    protected static final String CONFIG_NETWORK = "Network";

    /**
    * default constructor
    */
    public TCPFrontend (Vertx vertx, IGameServer gameServer) {
        this.vertx = vertx;
        this.gameServer = gameServer;
    }

    public void start () {
        //get number of threads
        int nOfThreads = Config.getSection(CONFIG_NETWORK).getIntOrDefault("nOfThreads", 4);

        NetServerOptions options = new NetServerOptions().setPort(getPort());

        String hostName = Config.getSection(CONFIG_NETWORK).getOrDefault("host", "0.0.0.0");

        if (!hostName.equals("0.0.0.0")) {
            options.setHost(hostName);
            LocalLogger.print("start tcp server on host " + hostName + " on port " + getPort());
        } else {
            LocalLogger.print("start tcp server on port " + getPort());
        }

        //Scaling - sharing TCP servers, see https://vertx.io/docs/vertx-core/java/#_scaling_sharing_tcp_servers
        for (int i = 0; i < nOfThreads; i++) {
            //create new tcp server
            NetServer server = this.vertx.createNetServer(options);

            //add connect handler
            server.connectHandler(new ConnectHandler(this.gameServer));

            //start server
            server.listen(this.getPort());

            servers.add(server);
        }
    }

    public void stop () {
        for (NetServer server : servers) {
            //shutdown tcp server
            server.close();
        }
    }

    protected int getPort () {
        return Config.getSection(CONFIG_NETWORK).getIntOrDefault("port", 20893);
    }

}
