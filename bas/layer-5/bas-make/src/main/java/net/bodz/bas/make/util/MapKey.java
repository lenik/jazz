package net.bodz.bas.make.util;

import java.util.Comparator;
import java.util.Objects;

import net.bodz.bas.meta.decl.NotNull;

public class MapKey<K, V>
        implements Comparator<MapKey<K, V>> {

    final Class<K> keyType;
    final Class<V> valueType;

    public MapKey(@NotNull Class<K> keyType, @NotNull Class<V> valueType) {
        this.keyType = keyType;
        this.valueType = valueType;
    }

    @NotNull
    public Class<K> getKeyType() {
        return keyType;
    }

    @NotNull
    public Class<V> getValueType() {
        return valueType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        MapKey<?, ?> mapKey = (MapKey<?, ?>) o;
        return Objects.equals(keyType, mapKey.keyType) && Objects.equals(valueType, mapKey.valueType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyType, valueType);
    }

    @Override
    public String toString() {
        return keyType.toString() + " -> " + valueType.toString();
    }

    @Override
    public int compare(MapKey<K, V> o1, MapKey<K, V> o2) {
        if (o1 == o2)
            return 0;
        if (o1 == null)
            return -1;
        if (o2 == null)
            return 1;
        String keyType1 = o1.keyType.toString();
        String keyType2 = o2.keyType.toString();
        int cmp = keyType1.compareTo(keyType2);
        if (cmp != 0)
            return cmp;

        String valueType1 = o1.valueType.toString();
        String valueType2 = o2.valueType.toString();
        cmp = valueType1.compareTo(valueType2);
        if (cmp != 0)
            return cmp;

        return 0;
    }

}
