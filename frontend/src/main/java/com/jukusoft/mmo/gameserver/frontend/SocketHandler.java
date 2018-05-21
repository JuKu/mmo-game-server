package com.jukusoft.mmo.gameserver.frontend;

import com.jukusoft.mmo.gameserver.core.server.IGameServer;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;

public class SocketHandler {

    protected final IGameServer gameServer;
    protected final NetSocket socket;

    //character id
    protected int cid = 0;
    protected int sectorID = 0;

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

    /**
    * if proxy has send cid than we have to join
    */
    public void join () {
        this.gameServer.join(this.cid, this.sectorID, content -> {
            socket.write(content);
        }, b -> {
            if (b) {
                //joined successfully

                //TODO: send message back to proxy server
            } else {
                //join failed

                //TODO: send message back to proxy server
            }
        });
    }

    public void onClose () {
        //connection was closed from proxy server

        //leave player, if cid is set
        if (this.cid >= 0) {
            this.gameServer.leave(this.cid);
        }
    }

}
