package com.jukusoft.mmo.gameserver.core.utils;

import com.jukusoft.mmo.gameserver.core.network.Protocol;
import io.vertx.core.buffer.Buffer;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageUtilsTest {

    @Test
    public void testConstructor () {
        new MessageUtils();
    }

    @Test
    public void testCreateMessage () {
        Buffer content = MessageUtils.createMsg(Protocol.MSG_TYPE_GS, Protocol.MSG_EXTENDED_TYPE_JOIN, 10);

        //check header
        assertEquals(Protocol.MSG_TYPE_GS, content.getByte(0));
        assertEquals(Protocol.MSG_EXTENDED_TYPE_JOIN, content.getByte(1));
        assertEquals(Protocol.MSG_PROTOCOL_VERSION, content.getShort(2));
        assertEquals(10, content.getInt(4));
    }

    @Test
    public void testCreateErrorMsg () {
        Buffer content = MessageUtils.createErrorMsg(Protocol.MSG_EXTENDED_TYPE_INTERNAL_SERVER_ERROR, 200);

        //check header
        assertEquals(Protocol.MSG_TYPE_ERROR, content.getByte(0));
        assertEquals(Protocol.MSG_EXTENDED_TYPE_INTERNAL_SERVER_ERROR, content.getByte(1));
        assertEquals(Protocol.MSG_PROTOCOL_VERSION, content.getShort(2));
        assertEquals(200, content.getInt(4));
    }

}
