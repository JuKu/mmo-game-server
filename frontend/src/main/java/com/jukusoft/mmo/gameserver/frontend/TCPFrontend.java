package com.jukusoft.mmo.gameserver.frontend;

import com.jukusoft.mmo.gameserver.core.server.IGameServer;
import io.vertx.core.Vertx;

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
    * default constructor
    */
    public TCPFrontend (Vertx vertx, IGameServer gameServer) {
        this.vertx = vertx;
        this.gameServer = gameServer;
    }

    public void start () {
        //
    }

    public void stop () {
        //
    }

}
