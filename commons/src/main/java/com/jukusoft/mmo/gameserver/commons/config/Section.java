package com.jukusoft.mmo.gameserver.commons.config;

import com.carrotsearch.hppc.IntObjectHashMap;
import com.carrotsearch.hppc.IntObjectMap;

public class Section {

    protected IntObjectMap<String> valuesMap = new IntObjectHashMap<>();

    public Section () {
        //
    }

    public void put (String key, String value) {
        valuesMap.put(key.hashCode(), value);
    }

    public String getOrDefault (String key, String defaultStr) {
        String value = this.valuesMap.get(key.hashCode());

        if (value == null) {
            return value;
        }

        return value;
    }

    public String get (String key) {
        return this.getOrDefault(key, null);
    }

}
