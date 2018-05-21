package com.jukusoft.mmo.gameserver.core.server;

import com.jukusoft.mmo.gameserver.core.network.MessageReceiver;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;

public interface IGameServer {

    /**
    * receive a network packet from proxy server
    */
    public void receive (Buffer content);

    /**
    * register new connection and join player to a specific sector
     *
     * @param cid character id
     * @param sectorID id of sector
     * @param receiver message receiver so gameserver can send messages back to proxy server
     * @param joinedHandler handler which is called if join was successfully or has failed, for example if sector doesnt exists
    */
    public void join (int cid, int sectorID, int instanceID, MessageReceiver<Buffer> receiver, Handler<Boolean> joinedHandler);

    /**
    * connection was closed
     *
     * @param cid character id
    */
    public void leave (int cid);

}
