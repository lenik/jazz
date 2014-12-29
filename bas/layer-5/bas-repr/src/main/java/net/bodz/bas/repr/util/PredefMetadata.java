package net.bodz.bas.repr.util;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.meta.stereo.IMetadata;
import net.bodz.bas.t.order.DefaultComparator;

public class PredefMetadata<E extends Predef<?, K>, K extends Comparable<K>>
        implements IMetadata {

    private final Class<E> type;
    private final int level;

    private Map<K, E> localKeyMap = new TreeMap<>(DefaultComparator.getInstance());
    private Map<String, E> localNameMap = new TreeMap<>(DefaultComparator.getInstance());

    private Map<K, E> keyMap = new TreeMap<>(DefaultComparator.getInstance());
    private Map<String, E> nameMap = new TreeMap<>(DefaultComparator.getInstance());

    public PredefMetadata(Class<E> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");

        int level = -1;
        Class<?> c = clazz;
        while (!c.equals(Predef.class)) {
            c = c.getSuperclass();
            if (c == null)
                throw new IllegalArgumentException("Not a subclass of Symbol: " + clazz);
            level++;
        }

        this.type = clazz;
        this.level = level;
    }

    public Class<E> getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public Map<K, E> getLocalKeyMap() {
        return Collections.unmodifiableMap(localKeyMap);
    }

    public Map<String, E> getLocalNameMap() {
        return Collections.unmodifiableMap(localNameMap);
    }

    public Collection<E> geLocalValues() {
        return Collections.unmodifiableCollection(localKeyMap.values());
    }

    public Map<K, E> getKeyMap() {
        return Collections.unmodifiableMap(keyMap);
    }

    public Map<String, E> getNameMap() {
        return Collections.unmodifiableMap(nameMap);
    }

    public Collection<E> getValues() {
        return Collections.unmodifiableCollection(keyMap.values());
    }

    public E ofKey(K key) {
        return keyMap.get(key);
    }

    public E ofName(String name) {
        return nameMap.get(name);
    }

    public void addValue(E value) {
        if (value == null)
            throw new NullPointerException("value");
        if (!value.getClass().equals(type))
            throw new IllegalArgumentException("Value is not a " + type.getName());

        K key = value.getKey();
        String name = value.getName();

        if (localKeyMap.containsKey(key))
            throw new DuplicatedKeyException(localKeyMap, key, "key");
        if (localNameMap.containsKey(name))
            throw new DuplicatedKeyException(localNameMap, key, "name");
        localKeyMap.put(key, value);
        localNameMap.put(name, value);

        Class<? extends Predef<?, ?>> c = type;
        while (true) {
            @SuppressWarnings("unchecked")//
            PredefMetadata<? super E, K> metadata = (PredefMetadata<? super E, K>) forClass(c);

            if (metadata.keyMap.containsKey(key))
                throw new DuplicatedKeyException(metadata.keyMap, key, "more key");
            if (metadata.nameMap.containsKey(name))
                throw new DuplicatedKeyException(metadata.keyMap, name, "more name");
            metadata.keyMap.put(key, value);
            metadata.nameMap.put(name, value);

            c = (Class<? extends Predef<?, ?>>) c.getSuperclass();
            if (c == null)
                break;
            if (Predef.class.equals(c))
                break;
        }

    }

    private static Map<Class<?>, PredefMetadata<?, ?>> classLocalMap;
    static {
        classLocalMap = new HashMap<>();
    }

    public static <V extends Predef<?, K>, K extends Comparable<K>> PredefMetadata<V, K> forClass(Class<V> type) {
        PredefMetadata<V, K> metadata = (PredefMetadata<V, K>) classLocalMap.get(type);
        if (metadata == null)
            classLocalMap.put(type, metadata = new PredefMetadata<>(type));
        return metadata;
    }

}
