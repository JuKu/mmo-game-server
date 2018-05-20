package com.jukusoft.mmo.gameserver.commons.version;

import org.junit.Test;

public class VersionTest {

    @Test
    public void testConstructor () {
        new Version(VersionTest.class);
    }

    @Test
    public void testConstructor1 () {
        new Version();
    }

    @Test (expected = NullPointerException.class)
    public void testNullConstructor () {
        new Version(null);
    }

}
