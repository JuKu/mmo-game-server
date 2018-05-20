package com.jukusoft.mmo.gameserver.commons.utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class JarUtils {

    protected JarUtils () {
        //
    }

    /**
    * get file instance of jar file, loaded from class
     *
     * NOTE: If class is not loaded from a jar file (e.q. if you execute JUnit tests) null is returned
     *
     * @return instance of jar file or null, if class wasnt loaded from a jar file
    */
    public static File getJarFileOfClass (Class<?> cls) {
        if (cls == null) {
            throw new NullPointerException("class cannot be null.");
        }

        //see also https://stackoverflow.com/questions/1983839/determine-which-jar-file-a-class-is-from

        //jar:file:/jdk/jre/lib/rt.jar!/java/lang/String.class --> file:/projects/classes/pkg/MyClass$1.class
        //String clsResource = cls.getName().replace('.', '/') + ".class";

        String classResource = cls.getName().replace('.', '/') + ".class";

        URL location = cls.getResource('/' + cls.getName().replace('.', '/') + ".class");

        String jarPath = location.getPath();

        String path = "";

        if (jarPath.startsWith("jar:file:")) {
            //its a jar file

            path = jarPath.substring("file:".length(), jarPath.lastIndexOf("!"));
        } else if (jarPath.startsWith("file:")) {
            if (jarPath.contains("!")) {
                path = jarPath.substring(0, jarPath.lastIndexOf("!"));
            } else {
                int tail = jarPath.indexOf(classResource);
                path = jarPath.substring(0, tail);
            }
        } else {
            //no jar file is loaded, e.q. if you execute tests
            return null;
        }

        return new File(path);
    }

    /**
    * get file path to jar file of class
     *
     * NOTE: If this is executed from JUnit tests, there isnt a jar file available yet
    */
    public static String getJarPath (Class<?> cls) throws URISyntaxException {
        return new File(cls.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
    }

}
