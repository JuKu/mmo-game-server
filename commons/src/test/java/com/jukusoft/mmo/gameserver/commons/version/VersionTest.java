package com.jukusoft.mmo.gameserver.commons.version;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void testGetter () {
        Version version = new Version(String.class);

        //check required attributes which are represent in D:\Program Files\Java\jdk1.8.0_91\jre\lib\rt.jar
        assertEquals(false, version.getVersion().contains("n/a"));
        assertEquals(true, version.getRevision().contains("n/a"));
        assertEquals(true, version.getBuildJdk().contains("n/a"));
        assertEquals(true, version.getBuildTime().contains("n/a"));
        assertEquals(false, version.getCreatedBy().contains("n/a"));
        assertEquals(true, version.getVendorID().contains("n/a"));
        assertEquals(false, version.getVendor().contains("n/a"));
        assertEquals(false, version.getFullVersion().isEmpty());
    }

}
