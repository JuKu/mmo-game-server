package com.jukusoft.mmo.gameserver.commons.version;

import com.jukusoft.mmo.gameserver.commons.utils.JarUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

public class Version {

    protected String revision = "n/a";
    protected String version = "n/a";
    protected String buildJdk = "n/a";
    protected String buildTime = "n/a";
    protected String createdBy = "n/a";//default: Created-By: Apache Maven 3.3.9
    protected String vendorID = "n/a";//default: Implementation-Vendor-Id: com.jukusoft

    /**
    * default constructor
    */
    public Version (Class<?> cls) {
        this.loadInfo(cls);
    }

    public Version () {
        //
    }

    protected void loadInfo (Class<?> cls) {
        //get jar file
        File file = JarUtils.getJarFileOfClass(cls);

        if (file == null) {
            //we cannot read manifest information, because class wasnt loaded from jar file
            return;
        }

        System.out.println("file path: " + file.getAbsolutePath());

        //open jar file
        try (JarFile jarFile = new JarFile(file)) {
            //get jar file attributes from jar manifest file
            final Attributes attrs = jarFile.getManifest().getMainAttributes();

            //get revision number
            this.revision = attrs.getValue("Implementation-Build");

            //get version number
            this.version = attrs.getValue("Implementation-Version");

            //get build jdk
            this.buildJdk = attrs.getValue("Build-Jdk");

            //get build time
            this.buildTime = attrs.getValue("Implementation-Time");

            //get build tool, if available
            this.createdBy = attrs.getValue("Created-By");

            if (this.createdBy == null) {
                this.createdBy = "n/a";
            }

            //get vendor id
            this.vendorID = attrs.getValue("Implementation-Vendor-Id");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
