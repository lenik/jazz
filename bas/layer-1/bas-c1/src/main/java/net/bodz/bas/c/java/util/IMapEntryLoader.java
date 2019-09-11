package net.bodz.bas.c.java.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.err.LazyLoadException;

public interface IMapEntryLoader<K, V> {

    V loadValue(K key)
            throws LazyLoadException;

    class fn {

        public static <K, k, v> NewHashMap<K, k, v> newHashMap() {
            return new NewHashMap<K, k, v>();
        }

        public static <K, k, v> NewTreeMap<K, k, v> newTreeMap() {
            return new NewTreeMap<K, k, v>();
        }

    }

    class NewHashMap<K, k, v>
            implements IMapEntryLoader<K, Map<k, v>> {

        @Override
        public Map<k, v> loadValue(K key)
                throws LazyLoadException {
            return new HashMap<k, v>();
        }

    }

    class NewTreeMap<K, k, v>
            implements IMapEntryLoader<K, Map<k, v>> {

        @Override
        public Map<k, v> loadValue(K key)
                throws LazyLoadException {
            return new TreeMap<k, v>();
        }

    }

}
