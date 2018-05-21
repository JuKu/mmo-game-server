package com.jukusoft.mmo.gameserver.core.network;

import com.jukusoft.mmo.gameserver.core.utils.MessageUtils;
import io.vertx.core.buffer.Buffer;

public class MessageFactory {

    protected MessageFactory () {
        //
    }

    public static Buffer createJoinSuccessMessage (int cid) {
        return MessageUtils.createMsg(Protocol.MSG_TYPE_GS, Protocol.MSG_EXTENDED_TYPE_JOIN_RESPONSE, cid);
    }

    public static Buffer createJoinFailedMessage (int cid) {
        return MessageUtils.createMsg(Protocol.MSG_TYPE_GS, Protocol.MSG_EXTENDED_TYPE_JOIN_FAILED, cid);
    }

}
