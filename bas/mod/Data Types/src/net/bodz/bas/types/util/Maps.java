package net.bodz.bas.types.util;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.types.IntRange;

public class Maps {

    public static <V> Map<Integer, V> toMap(final List<V> list) {
        class ListMap implements Map<Integer, V> {

            @Override
            public int size() {
                return list.size();
            }

            @Override
            public boolean isEmpty() {
                return list.isEmpty();
            }

            @Override
            public boolean containsKey(Object key) {
                if (!(key instanceof Integer))
                    return false;
                int index = (Integer) key;
                return index >= 0 && index < list.size();
            }

            @Override
            public boolean containsValue(Object value) {
                return list.contains(value);
            }

            @Override
            public Set<Integer> keySet() {
                return new IntRange(0, list.size());
            }

            @Override
            public Collection<V> values() {
                return list;
            }

            @Override
            public Set<Entry<Integer, V>> entrySet() {
                return new AbstractSet<Entry<Integer, V>>() {

                    @Override
                    public Iterator<Entry<Integer, V>> iterator() {
                        return new Iterator<Entry<Integer, V>>() {

                            int i = 0;

                            @Override
                            public boolean hasNext() {
                                return i < list.size();
                            }

                            @Override
                            public Entry<Integer, V> next() {
                                if (i >= list.size())
                                    return null;
                                final int idx = i++;
                                return new Entry<Integer, V>() {

                                    @Override
                                    public Integer getKey() {
                                        return idx;
                                    }

                                    @Override
                                    public V getValue() {
                                        return list.get(idx);
                                    }

                                    @Override
                                    public V setValue(V value) {
                                        return list.set(idx, value);
                                    }

                                };
                            }

                            @Override
                            public void remove() {
                                throw new UnsupportedOperationException();
                            }

                        };
                    }

                    @Override
                    public int size() {
                        return list.size();
                    }

                };
            }

            @Override
            public V get(Object key) {
                if (!(key instanceof Integer))
                    return null;
                int index = (Integer) key;
                if (index >= 0 && index < list.size())
                    return list.get(index);
                return null;
            }

            @Override
            public V put(Integer key, V value) {
                return list.set(key, value);
            }

            @Override
            public void putAll(Map<? extends Integer, ? extends V> m) {
                for (Integer k : m.keySet())
                    put(k, m.get(k));
            }

            /**
             * all entries with index > _index will be re-ordered.
             */
            @Override
            public V remove(Object _index) {
                if (!(_index instanceof Integer))
                    return null;
                int index = (Integer) _index;
                return list.remove(index);
            }

            @Override
            public void clear() {
                list.clear();
            }

        }

        return new ListMap();
    }

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
        BCharOut out = new BCharOut(map.size() * 100);
        if (keyf == null) {
            for (Entry<K, V> e : map.entrySet()) {
                K key = e.getKey();
                V value = e.getValue();
                out.printf(format, key, value);
            }
        } else {
            List<K> keys = new ArrayList<K>(map.keySet());
            Collections.sort(keys, keyf);
            for (K key : keys) {
                V value = map.get(key);
                out.printf(format, key, value);
            }
        }
        return out.toString();
    }

}
