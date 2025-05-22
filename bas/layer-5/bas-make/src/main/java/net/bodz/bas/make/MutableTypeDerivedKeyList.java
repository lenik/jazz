package net.bodz.bas.make;

import java.util.List;

import net.bodz.bas.make.util.ListKey;
import net.bodz.bas.meta.decl.NotNull;

public class MutableTypeDerivedKeyList<K, E>
        extends MutableTypeDerivedKeyCollection<ListKey<K, E>, K, List<E>, E>
        implements ITypeDerivedKeyList<K, E> {

    public MutableTypeDerivedKeyList(@NotNull Class<E> elementType, @NotNull K wrappedKey, List<E> data) {
        super(elementType, wrappedKey, data);
    }

    public MutableTypeDerivedKeyList(@NotNull Class<E> elementType, @NotNull Class<K> wrappedKeyType, @NotNull K wrappedKey, List<E> data) {
        super(elementType, wrappedKeyType, wrappedKey, data);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<ListKey<K, E>> getKeyType() {
        return (Class<ListKey<K, E>>) (Class<?>) ListKey.class;
    }

    @Override
    protected ListKey<K, E> createKey(K derivedKey) {
        return new ListKey<>(elementType, derivedKey);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<List<E>> getDataType() {
        return (Class<List<E>>) (Class<?>) List.class;
    }

}
