package com.jukusoft.mmo.gameserver.frontend;

import com.jukusoft.mmo.gameserver.commons.logger.LocalLogger;
import com.jukusoft.mmo.gameserver.core.server.IGameServer;
import com.jukusoft.mmo.gameserver.core.stream.BufferStream;
import io.vertx.core.Handler;
import io.vertx.core.net.NetSocket;

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

        //create buffer stream to support tcp streams
        BufferStream bufferStream = new BufferStream(socket, socket);

        //pause reading data
        bufferStream.pause();

        //create new socket handler
        SocketHandler handler = new SocketHandler(this.gameServer, bufferStream);
        handler.init();

        //add handler
        bufferStream.handler(handler::receive);
        bufferStream.endHandler(v -> handler.onClose());

        //resume reading data
        bufferStream.resume();
    }

}
