package com.jukusoft.mmo.gameserver.core;

import org.junit.Test;

public class CoreInfoTest {

    @Test
    public void testConstructor () {
        new CoreInfo();
    }

    @Test
    public void testPrintStartUpInfo () {
        CoreInfo.printStartUpInfo(CoreInfoTest.class);
    }

}
