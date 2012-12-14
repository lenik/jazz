package net.bodz.bas.c.java.util;

public class TextMaps {

    public static TextMap<String> create(String... keyValues) {
        TextMap<String> map = new LinkedHashTextMap<String>();
        for (int i = 0; i < keyValues.length; i += 2) {
            String key = keyValues[i];
            String value = keyValues[i + 1];
            map.put(key, value);
        }
        return map;
    }

}
