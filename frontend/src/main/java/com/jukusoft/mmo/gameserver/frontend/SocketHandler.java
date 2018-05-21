package com.jukusoft.mmo.gameserver.frontend;

import com.jukusoft.mmo.gameserver.commons.logger.LocalLogger;
import com.jukusoft.mmo.gameserver.commons.utils.ByteUtils;
import com.jukusoft.mmo.gameserver.core.network.MessageFactory;
import com.jukusoft.mmo.gameserver.core.network.Protocol;
import com.jukusoft.mmo.gameserver.core.server.IGameServer;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;

public class SocketHandler {

    protected final IGameServer gameServer;
    protected final NetSocket socket;

    //character id
    protected int cid = 0;
    protected int sectorID = 0;
    protected int instanceID = 0;

    public SocketHandler (IGameServer gameServer, NetSocket socket) {
        this.gameServer = gameServer;
        this.socket = socket;
    }

    public void init () {
        //
    }

    public void receive (Buffer content) {
        if (content == null) {
            throw new NullPointerException("buffer cannot be null.");
        }

        if (content.length() < Protocol.MSG_HEADER_LENGTH) {
            throw new IllegalArgumentException("buffer doesnt contains full header");
        }

        //get type
        byte type = content.getByte(0);
        byte extendedType = content.getByte(1);
        short version = content.getShort(2);

        //TODO: check protocol version

        //check, if user has choosen character
        if (this.cid == 0 && type != Protocol.MSG_TYPE_GS) {
            //drop message
            LocalLogger.print("Drop message, because user hasnt joined yet.");

            return;
        }

        //look for special proxy - gs messages
        if (type == Protocol.MSG_TYPE_GS) {
            //handle internal message
            this.handleInternalMessage(type, extendedType, content);
        }

        //user has joined already

        //redirect message to game server
        this.gameServer.receive(content);
    }

    protected void handleInternalMessage (byte type, byte extendedType, Buffer content) {
        int cid = content.getInt(4);

        switch (extendedType) {
            case Protocol.MSG_EXTENDED_TYPE_JOIN:
                //player join sector
                LocalLogger.print("player " + this.cid + " try to join sector " + this.sectorID + " on instance " + this.sectorID);

                //get sector
                this.sectorID = content.getInt(Protocol.MSG_BODY_OFFSET);
                this.instanceID = content.getInt(Protocol.MSG_BODY_OFFSET + 4);

                //try to join
                this.join();

                break;

            case Protocol.MSG_EXTENDED_TYPE_LEAVE:
                LocalLogger.print("player " + this.cid + " leaves sector " + this.sectorID + " on instance " + this.instanceID);

                //player leave sector
                this.leave();

                break;

            default:
                throw new IllegalStateException("Drop message, because extended type 0x" + ByteUtils.byteToHex(extendedType) + " is not supported yet.");
        }
    }

    /**
    * if proxy has send cid than we have to join
    */
    protected void join () {
        this.gameServer.join(this.cid, this.sectorID, this.instanceID, content -> {
            //send message back to proxy server
            socket.write(content);
        }, b -> {
            if (b) {
                //joined successfully

                LocalLogger.print("player " + this.cid + " joins sector " + this.sectorID + " on instance " + this.sectorID);

                //send message back to proxy server
                socket.write(MessageFactory.createJoinSuccessMessage(this.cid));
            } else {
                //join failed

                LocalLogger.print("Error! player " + this.cid + " couldnt join sector " + this.sectorID + " on instance " + this.sectorID);

                //send message back to proxy server
                socket.write(MessageFactory.createJoinFailedMessage(this.cid));
            }
        });
    }

    protected void leave () {
        this.gameServer.leave(this.cid);
    }

    public void onClose () {
        //connection was closed from proxy server

        //leave player, if cid is set
        if (this.cid >= 0) {
            this.gameServer.leave(this.cid);
        }
    }

}
