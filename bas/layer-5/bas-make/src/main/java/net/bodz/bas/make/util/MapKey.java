package net.bodz.bas.make.util;

import java.util.Objects;

import net.bodz.bas.meta.decl.NotNull;

public class MapKey<EK, EV, K>
        implements IMapKey<EK, EV, K> {

    final Class<? extends EK> elementKeyType;
    final Class<? extends EV> elementValueType;
    final K key;

    public MapKey(@NotNull Class<? extends EK> elementKeyType, @NotNull Class<? extends EV> elementValueType, @NotNull K key) {
        this.elementKeyType = elementKeyType;
        this.elementValueType = elementValueType;
        this.key = key;
    }

    @NotNull
    @Override
    public Class<? extends EK> getElementKeyType() {
        return elementKeyType;
    }

    @NotNull
    @Override
    public Class<? extends EV> getElementValueType() {
        return elementValueType;
    }

    @NotNull
    @Override
    public K getWrappedKey() {
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
        return "Map<" + elementKeyType.getName() + ", " + elementValueType.getName() + ", " + key + '>';
    }

}
