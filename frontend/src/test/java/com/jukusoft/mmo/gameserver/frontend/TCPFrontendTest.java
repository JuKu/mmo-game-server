package com.jukusoft.mmo.gameserver.frontend;

import com.jukusoft.mmo.gameserver.core.server.IGameServer;
import io.vertx.core.Vertx;
import org.junit.Test;
import org.mockito.Mockito;

public class TCPFrontendTest {

    @Test
    public void testConstructor () {
        Vertx vertx = Vertx.vertx();
        IGameServer gameServer = Mockito.mock(IGameServer.class);

        new TCPFrontend(vertx, gameServer);

        vertx.close();
    }

    @Test
    public void testStartAndStop () throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        IGameServer gameServer = Mockito.mock(IGameServer.class);

        //start server
        TCPFrontend server = new TCPFrontend(vertx, gameServer);
        server.start();

        Thread.sleep(1000);

        //stop server
        server.stop();

        vertx.close();
    }

}
