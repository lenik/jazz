package net.bodz.lilyfond.m2;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.primitive.IntegerComparator;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.t.order.ComparableComparator;

public class EnumClass<E extends Enum<?>> {

    private final Class<E> type;
    private final int level;

    private Map<Integer, Enum<?>> inheritedOrdinalMap = new TreeMap<>(IntegerComparator.INSTANCE);
    private Map<String, Enum<?>> inheritedNameMap = new TreeMap<>(ComparableComparator.getInstance());

    private Map<Integer, E> declaredOrdinalMap = new TreeMap<>(IntegerComparator.INSTANCE);
    private Map<String, E> declaredNameMap = new TreeMap<>(ComparableComparator.getInstance());

    private Map<Integer, /* ? extends */E> extendedOrdinalMap = new TreeMap<>(IntegerComparator.INSTANCE);
    private Map<String, /* ? extends */E> extendedNameMap = new TreeMap<>(ComparableComparator.getInstance());

    public EnumClass(Class<E> type) {
        if (type == null)
            throw new NullPointerException("type");

        int level = -1;
        Class<?> c = type;
        while (!c.equals(Enum.class)) {
            c = c.getSuperclass();
            if (c == null)
                throw new IllegalArgumentException("Not a subclass of Enum: " + type);
            level++;
        }

        this.type = type;
        this.level = level;
    }

    public Class<E> getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public Map<Integer, Enum<?>> getInheritedOrdinalMap() {
        return Collections.unmodifiableMap(inheritedOrdinalMap);
    }

    public Map<String, Enum<?>> getInheritedNameMap() {
        return Collections.unmodifiableMap(inheritedNameMap);
    }

    public Map<Integer, E> getDeclaredOrdinalMap() {
        return Collections.unmodifiableMap(declaredOrdinalMap);
    }

    public Map<String, E> getDeclaredNameMap() {
        return Collections.unmodifiableMap(declaredNameMap);
    }

    public Map<Integer, E> getExtendedOrdinalMap() {
        return Collections.unmodifiableMap(extendedOrdinalMap);
    }

    public Map<String, E> getExtendedNameMap() {
        return Collections.unmodifiableMap(extendedNameMap);
    }

    public Collection<Enum<?>> getInheritedValues() {
        return Collections.unmodifiableCollection(inheritedOrdinalMap.values());
    }

    public Collection<E> getDeclaredValues() {
        return Collections.unmodifiableCollection(declaredOrdinalMap.values());
    }

    public Collection<E> getExtendedValues() {
        return Collections.unmodifiableCollection(extendedOrdinalMap.values());
    }

    public Enum<?> getInheritedValue(int ordinal) {
        return inheritedOrdinalMap.get(ordinal);
    }

    public Enum<?> getInheritedValue(String name) {
        return inheritedNameMap.get(name);
    }

    public E getDeclaredValue(int ordinal) {
        return declaredOrdinalMap.get(ordinal);
    }

    public E getDeclaredValue(String name) {
        return declaredNameMap.get(name);
    }

    public E getExtendedValue(int ordinal) {
        return extendedOrdinalMap.get(ordinal);
    }

    public E getExtendedValue(String name) {
        return extendedNameMap.get(name);
    }

    public void addValue(E value) {
        if (value == null)
            throw new NullPointerException("value");
        if (!value.getClass().equals(type))
            throw new IllegalArgumentException("Value is not a " + type.getName());

        String name = value.name();
        int ordinal = value.ordinal();

        if (declaredOrdinalMap.containsKey(ordinal))
            throw new DuplicatedKeyException(declaredOrdinalMap, ordinal, "Enum ordinal");
        if (declaredNameMap.containsKey(name))
            throw new DuplicatedKeyException(declaredNameMap, name, "Enum name");

        declaredOrdinalMap.put(ordinal, value);
        declaredNameMap.put(name, value);

        Class<? extends Enum<?> /* super E */> c = type;
        while (true) {
            if (inheritedOrdinalMap.containsKey(ordinal))
                throw new DuplicatedKeyException(inheritedOrdinalMap, ordinal, "Enum ordinal");
            if (inheritedNameMap.containsKey(name))
                throw new DuplicatedKeyException(inheritedNameMap, name, "Enum name");

            this.inheritedOrdinalMap.put(ordinal, value);
            this.inheritedNameMap.put(name, value);

            @SuppressWarnings("unchecked")//
            EnumClass<? super E> ancestor = (EnumClass<? super E>) getEnumClass(c);

            if (ancestor.extendedOrdinalMap.containsKey(ordinal))
                throw new DuplicatedKeyException(ancestor.extendedOrdinalMap, ordinal, "Enum ordinal");
            if (ancestor.extendedNameMap.containsKey(name))
                throw new DuplicatedKeyException(ancestor.extendedNameMap, name, "Enum name");

            ancestor.extendedOrdinalMap.put(ordinal, value);
            ancestor.extendedNameMap.put(name, value);

            c = (Class<? extends Enum<?>>) c.getSuperclass();
            if (c == null)
                break;
            if (Enum.class.equals(c))
                break;
        }

    }

    private static Map<Class<? extends Enum<?>>, EnumClass<?>> classLocalMap;
    static {
        classLocalMap = new HashMap<>();
    }

    public static <E extends Enum<?>> EnumClass<E> getEnumClass(Class<E> type) {
        EnumClass<E> enumClass = (EnumClass<E>) classLocalMap.get(type);
        return enumClass;
    }

}
