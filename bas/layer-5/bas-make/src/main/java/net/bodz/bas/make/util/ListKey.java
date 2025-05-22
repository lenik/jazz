package net.bodz.bas.make.util;

import java.util.Objects;

import net.bodz.bas.meta.decl.NotNull;

public class ListKey<K, E>
        implements ICollectionKey<K, E> {

    final Class<E> elementType;
    final K key;

    public ListKey(@NotNull Class<E> elementType, @NotNull K key) {
        this.elementType = elementType;
        this.key = key;
    }

    @NotNull
    @Override
    public Class<E> getElementType() {
        return elementType;
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
        ListKey<?, ?> listKey = (ListKey<?, ?>) o;
        return Objects.equals(elementType, listKey.elementType) && Objects.equals(key, listKey.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(0x1451a8b7, elementType, key);
    }

    @Override
    public String toString() {
        return "List<" + elementType.getName() + ", " + key + '>';
    }

}
