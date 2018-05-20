package com.jukusoft.mmo.gameserver.frontend;

import com.jukusoft.mmo.gameserver.core.server.IGameServer;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;

public class SocketHandler {

    protected final IGameServer gameServer;
    protected final NetSocket socket;

    //character id
    protected int cid = 0;

    public SocketHandler (IGameServer gameServer, NetSocket socket) {
        this.gameServer = gameServer;
        this.socket = socket;
    }

    public void init () {
        //
    }

    public void receive (Buffer content) {
        //
    }

    public void onClose () {
        //connection was closed from proxy server
    }

}
