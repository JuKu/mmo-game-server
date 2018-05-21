package com.jukusoft.mmo.gameserver.core.network;

import io.vertx.core.buffer.Buffer;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageFactoryTest {

    @Test
    public void testConstructor () {
        new MessageFactory();
    }

    @Test
    public void testCreateJoinSuccessMessage () {
        Buffer content = MessageFactory.createJoinSuccessMessage(10);

        //check header
        assertEquals(Protocol.MSG_TYPE_GS, content.getByte(0));
        assertEquals(Protocol.MSG_EXTENDED_TYPE_JOIN_RESPONSE, content.getByte(1));
        assertEquals(Protocol.MSG_PROTOCOL_VERSION, content.getShort(2));
        assertEquals(10, content.getInt(4));
    }

    @Test
    public void testCreateJoinFailedMessage () {
        Buffer content = MessageFactory.createJoinFailedMessage(10);

        //check header
        assertEquals(Protocol.MSG_TYPE_GS, content.getByte(0));
        assertEquals(Protocol.MSG_EXTENDED_TYPE_JOIN_FAILED, content.getByte(1));
        assertEquals(Protocol.MSG_PROTOCOL_VERSION, content.getShort(2));
        assertEquals(10, content.getInt(4));
    }

}
