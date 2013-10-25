package net.bodz.bas.c.java.util;

import java.util.Map;
import java.util.Set;

public interface TextMap<V>
        extends Map<String, V> {

    class fn {

        public static String searchUnusedKey(Set<String> map, String prefix) {
            if (!map.contains(prefix))
                return prefix;
            else
                return searchUnusedKey(map, prefix, 0);
        }

        public static String searchUnusedKey(Set<String> map, String prefix, int startIndex) {
            while (true) {
                if (startIndex >= Integer.MAX_VALUE)
                    throw new RuntimeException("Index used out. prefix=" + prefix);

                String key = prefix + startIndex;

                if (!map.contains(key))
                    return key;
                startIndex++;
            }
        }

    }

}
