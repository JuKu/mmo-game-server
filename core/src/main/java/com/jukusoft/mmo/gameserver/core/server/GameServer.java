package com.jukusoft.mmo.gameserver.core.server;

import com.carrotsearch.hppc.IntIntHashMap;
import com.carrotsearch.hppc.IntIntMap;
import com.carrotsearch.hppc.IntObjectMap;
import com.jukusoft.mmo.gameserver.core.network.MessageReceiver;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;

public class GameServer implements IGameServer {

    //this map stores on which sector character is
    protected IntIntMap cidToSectorIDMap = new IntIntHashMap();

    //this map stores on which instance character is
    protected IntIntMap cidToInstanceIDMap = new IntIntHashMap();

    @Override
    public void receive(Buffer content) {

    }

    @Override
    public void join(int cid, int sectorID, int instanceID, MessageReceiver<Buffer> receiver, Handler<Boolean> joinedHandler) {
        //TODO: check, if sector instance is running on this server, else fail

        this.cidToSectorIDMap.put(cid, sectorID);
        this.cidToInstanceIDMap.put(cid, instanceID);

        //TODO: load player (character) data

        //TODO: set player into sector

        //TODO: call handler
    }

    @Override
    public void leave(int cid) {
        int sectorID = this.cidToSectorIDMap.get(cid);
        int instanceID = this.cidToInstanceIDMap.get(cid);

        //TODO: remove player from sector
    }

}
