package net.bodz.bas.t.enm;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.meta.stereo.IMetadata;
import net.bodz.bas.t.order.DefaultComparator;

public class EnumMetadata<E extends Enum<?, K>, K extends Comparable<K>>
        implements IMetadata {

    private final Class<E> type;
    private final int level;

    private Map<K, E> local = new TreeMap<>(DefaultComparator.getInstance());
    private Map<K, E> extension = new TreeMap<>(DefaultComparator.getInstance());

    public EnumMetadata(Class<E> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");

        int level = -1;
        Class<?> c = clazz;
        while (!c.equals(Enum.class)) {
            c = c.getSuperclass();
            if (c == null)
                throw new IllegalArgumentException("Not a subclass of Enum: " + clazz);
            level++;
        }

        this.type = clazz;
        this.level = level;
    }

    public Class<E> getEnumType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public Map<K, E> localMap() {
        return Collections.unmodifiableMap(local);
    }

    public Collection<E> localValues() {
        return Collections.unmodifiableCollection(local.values());
    }

    public Map<K, E> map() {
        return Collections.unmodifiableMap(extension);
    }

    public Collection<E> values() {
        return Collections.unmodifiableCollection(extension.values());
    }

    public E getLocalValue(K key) {
        return local.get(key);
    }

    public E getExtensionValue(K key) {
        return extension.get(key);
    }

    public void addValue(E value) {
        if (value == null)
            throw new NullPointerException("value");
        if (!value.getClass().equals(type))
            throw new IllegalArgumentException("Value is not a " + type.getName());

        K key = value.getKey();
        if (local.containsKey(key))
            throw new DuplicatedKeyException(local, key, "key");
        local.put(key, value);

        Class<? extends Enum<?, ?>> c = type;
        while (true) {
            @SuppressWarnings("unchecked")
            EnumMetadata<? super E, K> metadata = (EnumMetadata<? super E, K>) forClass(c);

            if (metadata.extension.containsKey(key))
                throw new DuplicatedKeyException(metadata.extension, key, "Enum name");
            metadata.extension.put(key, value);

            c = (Class<? extends Enum<?, ?>>) c.getSuperclass();
            if (c == null)
                break;
            if (Enum.class.equals(c))
                break;
        }

    }

    private static Map<Class<?>, EnumMetadata<?, ?>> classLocalMap;
    static {
        classLocalMap = new HashMap<>();
    }

    public static <V extends Enum<?, K>, K extends Comparable<K>> EnumMetadata<V, K> forClass(Class<V> type) {
        EnumMetadata<V, K> metadata = (EnumMetadata<V, K>) classLocalMap.get(type);
        if (metadata == null)
            classLocalMap.put(type, metadata = new EnumMetadata<>(type));
        return metadata;
    }

}
