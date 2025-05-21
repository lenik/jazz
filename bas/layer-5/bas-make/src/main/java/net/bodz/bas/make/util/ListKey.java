package net.bodz.bas.make.util;

import net.bodz.bas.meta.decl.NotNull;

public class ListKey<K, E> {

    final Class<E> elementType;
    final K key;

    public ListKey(@NotNull Class<E> elementType, @NotNull K key) {
        this.elementType = elementType;
        this.key = key;
    }
}
