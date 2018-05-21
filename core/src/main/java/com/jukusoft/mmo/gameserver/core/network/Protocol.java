package com.jukusoft.mmo.gameserver.core.network;

public class Protocol {

    /**
     * protocol information
     */
    public static final int MSG_HEADER_LENGTH = 8;//header length in bytes
    public static final int MSG_HEADER_CID_POS = 4;
    public static final int MSG_BODY_OFFSET = 8;
    public static final short MSG_PROTOCOL_VERSION = 1;

    /**
     * protocol types
     */

    //message types
    public static final byte MSG_TYPE_GS = 0x01;
    public static final byte MSG_TYPE_AUTH = 0x02;

    public static final byte MSG_TYPE_ERROR = 0x0B;

    //type: 0x01 - reserved for proxy - game server communication (client is not allowed to send such messages)
    public static final byte MSG_EXTENDED_TYPE_JOIN = 0x01;//player joins sector
    public static final byte MSG_EXTENDED_TYPE_LEAVE = 0x02;//player leaves sector
    public static final byte MSG_EXTENDED_TYPE_NETWORK_INIT = 0x03;
    public static final byte MSG_EXTENDED_TYPE_JOIN_RESPONSE = 0x04;
    public static final byte MSG_EXTENDED_TYPE_JOIN_FAILED = 0x05;
    public static final byte MSG_EXTENDED_TYPE_INTERNAL_SERVER_ERROR = 0x06;

}
