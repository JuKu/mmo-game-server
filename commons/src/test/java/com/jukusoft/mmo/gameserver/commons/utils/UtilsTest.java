package com.jukusoft.mmo.gameserver.commons.utils;

import com.jukusoft.mmo.gameserver.commons.utils.Utils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void testConstructor () {
        new Utils();
    }

    @Test (expected = NullPointerException.class)
    public void testPrintNullSection () {
        Utils.printSection(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPrintEmptySection () {
        Utils.printSection("");
    }

    @Test
    public void testPrintSection () {
        Utils.printSection("my-section");
    }

    @Test
    public void testIsRootUser () {
        assertEquals(false, Utils.isRootUser());
    }

    @Test
    public void testIsRootUser1 () {
        System.setProperty("user.name", "root");
        System.setProperty("user.home", "/root");

        assertEquals(true, Utils.isRootUser());
    }

}
