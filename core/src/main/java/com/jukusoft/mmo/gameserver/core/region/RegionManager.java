package com.jukusoft.mmo.gameserver.core.region;

import com.jukusoft.mmo.gameserver.core.network.MessageReceiver;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;

public interface RegionManager {

    //TODO: load player (character) data

    //TODO: set player into sector

    //TODO: call handler

    public void join (int cid, int regionID, int instanceID, float x, float y, float z, MessageReceiver<Buffer> receiver, Handler<Boolean> joinedHandler);

    public void leave (int cid);

    public boolean contains (int regionID, int instanceID);

    public void startRegion (int regionID, int instanceID);

    public void stopRegion (int regionID, int instanceID);

    public void receive (int cid, int regionID, int instanceID, Buffer content);

}
