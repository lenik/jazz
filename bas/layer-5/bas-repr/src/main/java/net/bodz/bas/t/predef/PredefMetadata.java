package net.bodz.bas.t.predef;

import java.util.*;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.meta.stereo.IMetadata;
import net.bodz.bas.t.order.DefaultComparator;

public class PredefMetadata<E extends Predef<?, K>, K extends Comparable<K>>
        implements IMetadata {

    static Random random = new Random();

    private final Class<E> itemClass;
    private final Class<?> stopClass;
    private final int level;

    private Map<K, E> localKeyMap = new TreeMap<>(DefaultComparator.getInstance());
    private Map<String, E> localNameMap = new TreeMap<>(DefaultComparator.getInstance());
    private List<E> localValueList = new ArrayList<>();

    private Map<K, E> keyMap = new TreeMap<>(DefaultComparator.getInstance());
    private Map<String, E> nameMap = new TreeMap<>(DefaultComparator.getInstance());
    private List<E> valueList = new ArrayList<>();

    PredefMetadata(Class<E> itemClass) {
        this(itemClass, Predef.class);
    }

    PredefMetadata(Class<E> itemClass, Class<?> stopClass) {
        if (itemClass == null)
            throw new NullPointerException("itemClass");
        if (stopClass == null)
            throw new NullPointerException("stopClass");
        this.itemClass = itemClass;
        this.stopClass = stopClass;

        int level = -1;
        Class<?> c = itemClass;
        while (!c.equals(stopClass)) {
            c = c.getSuperclass();
            if (c == null)
                throw new IllegalArgumentException("Not a subclass of Symbol: " + itemClass);
            level++;
        }

        this.level = level;
    }

    public Class<E> getItemClass() {
        return itemClass;
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

    public List<E> geLocalValueList() {
        return Collections.unmodifiableList(localValueList);
    }

    public synchronized E getAnyLocalValue() {
        int n = localValueList.size();
        int index = random.nextInt(n);
        return localValueList.get(index);
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

    public List<E> getValueList() {
        return Collections.unmodifiableList(valueList);
    }

    public synchronized E getAnyValue() {
        int n = valueList.size();
        int index = random.nextInt(n);
        return valueList.get(index);
    }

    public E ofKey(K key) {
        return keyMap.get(key);
    }

    public E ofName(String name) {
        return nameMap.get(name);
    }

    public synchronized void addValue(E value) {
        if (value == null)
            throw new NullPointerException("value");
        if (!value.getClass().equals(itemClass))
            throw new IllegalArgumentException("Value is not a " + itemClass.getName());

        K key = value.getKey();
        String name = value.getName();

        if (localKeyMap.containsKey(key))
            throw new DuplicatedKeyException(localKeyMap, key, "key");
        if (localNameMap.containsKey(name))
            throw new DuplicatedKeyException(localNameMap, key, "name");
        localKeyMap.put(key, value);
        localNameMap.put(name, value);
        localValueList.add(value);

        Class<E> c = itemClass;
        while (true) {
            PredefMetadata<E, K> metadata = forClass(c);

            if (metadata.keyMap.containsKey(key))
                throw new DuplicatedKeyException(metadata.keyMap, key, "more key");
            if (metadata.nameMap.containsKey(name))
                throw new DuplicatedKeyException(metadata.nameMap, name, "more name");
            metadata.keyMap.put(key, value);
            metadata.nameMap.put(name, value);
            metadata.valueList.add(value);

            c = (Class<E>) c.getSuperclass();
            if (c == null)
                break;
            if (stopClass.equals(c))
                break;
        }

    }

    private static Map<Class<?>, PredefMetadata<?, ?>> classLocalMap;
    static {
        classLocalMap = new HashMap<>();
    }

    public static <E extends Predef<?, K>, K extends Comparable<K>> //
    PredefMetadata<E, K> forClass(Class<E> type) {
        PredefMetadata<E, K> metadata = (PredefMetadata<E, K>) classLocalMap.get(type);
        if (metadata == null)
            classLocalMap.put(type, metadata = new PredefMetadata<>(type));
        return metadata;
    }

}
