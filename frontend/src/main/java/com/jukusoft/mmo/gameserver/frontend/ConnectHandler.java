package com.jukusoft.mmo.gameserver.frontend;

import com.jukusoft.mmo.gameserver.commons.logger.LocalLogger;
import com.jukusoft.mmo.gameserver.core.server.IGameServer;
import io.vertx.core.Handler;
import io.vertx.core.net.NetSocket;

import java.util.logging.Level;

public class ConnectHandler implements Handler<NetSocket> {

    protected final IGameServer gameServer;

    public ConnectHandler (IGameServer gameServer) {
        this.gameServer = gameServer;
    }

    @Override
    public void handle(NetSocket socket) {
        //log ip and port
        final String ip = socket.remoteAddress().host();
        final int clientPort = socket.remoteAddress().port();
        LocalLogger.print("New connection " + ip + ":" + clientPort);

        //create new socket handler
        SocketHandler handler = new SocketHandler(this.gameServer, socket);
        handler.init();

        //add handler
        socket.handler(buffer -> handler.receive(buffer));
        socket.closeHandler(v -> handler.onClose());
    }

}
