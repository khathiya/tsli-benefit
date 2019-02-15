package com.tsli.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ConfigBundle {
	private static final String CONFIG = "config";
    private static HashMap map = new HashMap();

    static {
        ResourceBundle configResource = ResourceBundle.getBundle(CONFIG);
        Enumeration keys = configResource.getKeys();
        String key = null;
        while(keys.hasMoreElements()) {
            key = (String)keys.nextElement();
            map.put(key, configResource.getString(key));
        }
    }

    public static String getValue(String name) {
        String value = null;
        if (map.containsKey(name)){
            value = (String)map.get(name);
        }
        return value;
    }
}
