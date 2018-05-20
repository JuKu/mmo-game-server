package com.jukusoft.mmo.gameserver.commons.utils;

import com.jukusoft.mmo.gameserver.commons.version.Version;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JarUtilsTest {

    @Test
    public void testConstructor () {
        new JarUtils();
    }

    @Test (expected = NullPointerException.class)
    public void testGetNullJarFile () {
        JarUtils.getJarFileOfClass(null);
    }

    @Test
    public void testGetJarFile () {
        File file = JarUtils.getJarFileOfClass(String.class);

        System.out.println("jar file: " + file.getAbsolutePath());

        assertNotNull(file);
        assertEquals(true, file.getAbsolutePath().endsWith("jar"));
    }

    @Test
    public void testGetJarPath () throws URISyntaxException {
        String path = JarUtils.getJarPath(JarUtilsTest.class);
        System.out.println("jar path: " + path);

        assertNotNull(path);
        assertEquals(false, path.isEmpty());
    }

}
