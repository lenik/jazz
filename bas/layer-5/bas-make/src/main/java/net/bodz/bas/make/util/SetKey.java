package net.bodz.bas.make.util;

import java.util.Objects;

import net.bodz.bas.meta.decl.NotNull;

public class SetKey<E, K>
        implements ICollectionKey<E, K> {

    final Class<? extends E> elementType;
    final K key;

    public SetKey(@NotNull Class<? extends E> elementType, @NotNull K key) {
        this.elementType = elementType;
        this.key = key;
    }

    @NotNull
    @Override
    public Class<? extends E> getElementType() {
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
        SetKey<?, ?> setKey = (SetKey<?, ?>) o;
        return Objects.equals(elementType, setKey.elementType) && Objects.equals(key, setKey.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(0xe86e9f3b, elementType, key);
    }

    @Override
    public String toString() {
        return "Set<" + elementType.getName() + ", " + key + '>';
    }

}
