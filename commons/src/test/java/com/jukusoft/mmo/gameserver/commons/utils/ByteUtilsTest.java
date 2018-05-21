package com.jukusoft.mmo.gameserver.commons.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ByteUtilsTest {

    @Test
    public void testConstructor () {
        new ByteUtils();
    }

    @Test
    public void testBytesToHex () {
        assertEquals("0102", ByteUtils.bytesToHex(new byte[]{ 0x01, 0x02 }));
        assertEquals("CDEF", ByteUtils.bytesToHex(new byte[]{ (byte) 0xcd, (byte) 0xef }));
    }

    @Test
    public void testByteToHex () {
        assertEquals("00", ByteUtils.byteToHex((byte) 0x00));
        assertEquals("01", ByteUtils.byteToHex((byte) 0x01));
        assertEquals("02", ByteUtils.byteToHex((byte) 0x02));

        assertEquals("0A", ByteUtils.byteToHex((byte) 0x0A));
        assertEquals("EE", ByteUtils.byteToHex((byte) 0xEE));

        assertEquals("FF", ByteUtils.byteToHex((byte) 0xFF));
    }

    @Test
    public void testByteToUnsignedInt () {
        assertEquals(0, ByteUtils.byteToUnsignedInt((byte) 0x00));
        assertEquals(1, ByteUtils.byteToUnsignedInt((byte) 0x01));
        assertEquals(2, ByteUtils.byteToUnsignedInt((byte) 0x02));
        assertEquals(3, ByteUtils.byteToUnsignedInt((byte) 0x03));
        assertEquals(4, ByteUtils.byteToUnsignedInt((byte) 0x04));
        assertEquals(5, ByteUtils.byteToUnsignedInt((byte) 0x05));
        assertEquals(6, ByteUtils.byteToUnsignedInt((byte) 0x06));
        assertEquals(7, ByteUtils.byteToUnsignedInt((byte) 0x07));
        assertEquals(8, ByteUtils.byteToUnsignedInt((byte) 0x08));
        assertEquals(9, ByteUtils.byteToUnsignedInt((byte) 0x09));
        assertEquals(10, ByteUtils.byteToUnsignedInt((byte) 0x0A));
        assertEquals(11, ByteUtils.byteToUnsignedInt((byte) 0x0B));
        assertEquals(12, ByteUtils.byteToUnsignedInt((byte) 0x0C));
        assertEquals(13, ByteUtils.byteToUnsignedInt((byte) 0x0D));
        assertEquals(14, ByteUtils.byteToUnsignedInt((byte) 0x0E));
        assertEquals(15, ByteUtils.byteToUnsignedInt((byte) 0x0F));

        assertEquals(16, ByteUtils.byteToUnsignedInt((byte) 0x10));

        assertEquals(239, ByteUtils.byteToUnsignedInt((byte) 0xEF));
        assertEquals(255, ByteUtils.byteToUnsignedInt((byte) 0xFF));
    }

}
