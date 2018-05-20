package com.jukusoft.mmo.gameserver.commons.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SectionTest {

    @Test
    public void testConstructor () {
        new Section();
    }

    @Test
    public void testGetUnknownKey () {
        Section section = new Section();
        assertEquals(null, section.get("test-key"));
    }

    @Test
    public void testGetOrDefault () {
        Section section = new Section();

        assertEquals(null, section.getOrDefault("test-key", null));
        assertEquals("", section.getOrDefault("test-key", ""));
    }

    @Test
    public void testGetOrDefault1 () {
        WritableSection section = new WritableSection();

        assertEquals(null, section.getOrDefault("test-key", null));
        assertEquals("", section.getOrDefault("test-key", ""));

        section.put("test-key", "test-value");

        assertEquals("test-value", section.get("test-key"));
        assertEquals("test-value", section.getOrDefault("test-key", ""));
    }

}
