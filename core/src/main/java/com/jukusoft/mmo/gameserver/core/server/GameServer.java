package com.jukusoft.mmo.gameserver.core.server;

import com.carrotsearch.hppc.IntIntHashMap;
import com.carrotsearch.hppc.IntIntMap;
import com.jukusoft.mmo.gameserver.commons.logger.LocalLogger;
import com.jukusoft.mmo.gameserver.core.network.MessageReceiver;
import com.jukusoft.mmo.gameserver.core.region.RegionManager;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;

public class GameServer implements IGameServer {

    //this map stores on which sector character is
    protected IntIntMap cidToRegionIDMap = new IntIntHashMap();

    //this map stores on which instance character is
    protected IntIntMap cidToInstanceIDMap = new IntIntHashMap();

    protected final RegionManager regionManager;

    public GameServer (RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    @Override
    public void receive(Buffer content) {
        //get cid
        int cid = content.getInt(4);

        //find region of character
        int regionID = this.cidToRegionIDMap.get(cid);
        int instanceID = this.cidToInstanceIDMap.get(cid);

        //send message to region
        this.regionManager.receive(cid, regionID, instanceID, content);
    }

    @Override
    public void join(int cid, final int regionID, final int instanceID, float x, float y, float z, MessageReceiver<Buffer> receiver, Handler<Boolean> joinedHandler) {
        //check, if sector instance is running on this server, else fail
        if (!this.regionManager.contains(regionID, instanceID)) {
            LocalLogger.warn("player (cid: " + cid + ") cannot join region " + regionID + " on instance " + instanceID + ", because this region is not running on this server.");
            joinedHandler.handle(false);

            return;
        }

        this.cidToRegionIDMap.put(cid, regionID);
        this.cidToInstanceIDMap.put(cid, instanceID);

        this.regionManager.join(cid, regionID, instanceID, x, y, z, receiver, joinedHandler);
    }

    @Override
    public void leave(int cid) {
        int regionID = this.cidToRegionIDMap.get(cid);
        int instanceID = this.cidToInstanceIDMap.get(cid);

        //TODO: remove player from region
    }

}
