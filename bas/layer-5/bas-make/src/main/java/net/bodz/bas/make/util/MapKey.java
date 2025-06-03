package net.bodz.bas.make.util;

import java.util.Objects;

import net.bodz.bas.make.tdk.ITypeDerivedKey;
import net.bodz.bas.meta.decl.NotNull;

public class MapKey<E, EK, K>
        implements IMapKey<E, EK, K>,
                   ITypeDerivedKey<E, K> {

    final Class<? extends EK> elementKeyType;
    final Class<? extends E> elementValueType;
    final Class<? extends K> keyType;
    final K key;

    @SuppressWarnings("unchecked")
    public MapKey(//
            @NotNull Class<? extends E> elementValueType, //
            @NotNull Class<? extends EK> elementKeyType, //
            @NotNull K key) {
        this(elementValueType, elementKeyType, (Class<? extends K>) key.getClass(), key);
    }

    public MapKey(//
            @NotNull Class<? extends E> elementValueType, //
            @NotNull Class<? extends EK> elementKeyType, //
            @NotNull Class<? extends K> keyType, //
            @NotNull K key) {
        this.elementValueType = elementValueType;
        this.elementKeyType = elementKeyType;
        this.keyType = keyType;
        this.key = key;
    }

    @NotNull
    @Override
    public Class<? extends EK> getElementKeyType() {
        return elementKeyType;
    }

    @NotNull
    @Override
    public Class<? extends E> getElementValueType() {
        return elementValueType;
    }

    @NotNull
    @Override
    public Class<? extends K> getWrappedKeyType() {
        return keyType;
    }

    @NotNull
    @Override
    public K getWrappedKey() {
        return key;
    }

    @NotNull
    @Override
    public Class<? extends E> getDerivedFromType() {
        return elementValueType;
    }

    @NotNull
    @Override
    public Class<? extends K> getDerivedKeyType() {
        return keyType;
    }

    @NotNull
    @Override
    public K getDerivedKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        MapKey<?, ?, ?> mapKey = (MapKey<?, ?, ?>) o;
        return Objects.equals(elementKeyType, mapKey.elementKeyType) //
                && Objects.equals(elementValueType, mapKey.elementValueType) //
                && Objects.equals(key, mapKey.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(0x6c8a7a30, elementKeyType, elementValueType, key);
    }

    @Override
    public String toString() {
        return "Map<" + elementKeyType.getName() + ", " + elementValueType.getName() + ">[" + key + ']';
    }

}
