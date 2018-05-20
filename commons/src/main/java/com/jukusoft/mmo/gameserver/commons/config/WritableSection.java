package com.jukusoft.mmo.gameserver.commons.config;

public class WritableSection extends Section {

    public void put (String key, String value) {
        valuesMap.put(key.hashCode(), value);
    }

}
