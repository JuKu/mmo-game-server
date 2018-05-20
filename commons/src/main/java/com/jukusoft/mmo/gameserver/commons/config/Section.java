package com.jukusoft.mmo.gameserver.commons.config;

import com.carrotsearch.hppc.IntObjectHashMap;
import com.carrotsearch.hppc.IntObjectMap;

/**
* config section
 *
 * This class is readonly!
*/
public class Section {

    protected IntObjectMap<String> valuesMap = new IntObjectHashMap<>();

    public Section () {
        //
    }

    public String getOrDefault (String key, String defaultStr) {
        String value = this.valuesMap.get(key.hashCode());

        if (value == null) {
            return defaultStr;
        }

        return value;
    }

    public String get (String key) {
        return this.getOrDefault(key, null);
    }

    public int getIntOrDefault (String key, int defaultValue) {
        String value = this.get(key);

        if (value == null) {
            return defaultValue;
        }

        return Integer.parseInt(value);
    }

}
