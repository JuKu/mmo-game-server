package com.jukusoft.mmo.gameserver.commons.config;

import java.io.File;
import java.io.IOException;

public interface ConfigLoader {

    public void load (File file) throws IOException;

}
