package net.bodz.bas.collection.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Dump map to text contents.
 */
public class DumpMap {

    public static <K, V> String dump(Map<K, V> map) {
        return dump(map, null);
    }

    public static <K, V> String dump(Map<K, V> map, Comparator<? super K> keyf) {
        return dump("%s=%s\n", map, keyf);
    }

    public static <K, V> String dump(String format, Map<K, V> map) {
        return dump(format, map, null);
    }

    public static <K, V> String dump(String format, Map<K, V> map, Comparator<? super K> keyf) {
        if (format == null)
            throw new NullPointerException("format");
        if (map == null)
            throw new NullPointerException("map");
        StringBuffer buffer = new StringBuffer(map.size() * 100);
        if (keyf == null) {
            for (Entry<K, V> e : map.entrySet()) {
                K key = e.getKey();
                V value = e.getValue();
                buffer.append(String.format(format, key, value));
            }
        } else {
            List<K> keys = new ArrayList<K>(map.keySet());
            Collections.sort(keys, keyf);
            for (K key : keys) {
                V value = map.get(key);
                buffer.append(String.format(format, key, value));
            }
        }
        return buffer.toString();
    }

}
