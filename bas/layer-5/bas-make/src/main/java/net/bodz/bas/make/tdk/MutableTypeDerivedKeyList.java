package net.bodz.bas.make.tdk;

import java.util.List;

import net.bodz.bas.make.util.ListKey;
import net.bodz.bas.meta.decl.NotNull;

public class MutableTypeDerivedKeyList<E, K>
        extends MutableTypeDerivedKeyCollection<E, ListKey<E, K>, K, List<E>>
        implements ITypeDerivedKeyList<E, K> {

    public MutableTypeDerivedKeyList(@NotNull Class<? extends E> elementType, @NotNull K derivedKey) {
        super(elementType, derivedKey, null);
    }

    public MutableTypeDerivedKeyList(@NotNull Class<? extends E> elementType, @NotNull K derivedKey, List<E> data) {
        super(elementType, derivedKey, data);
    }

    public MutableTypeDerivedKeyList(@NotNull Class<? extends E> elementType, @NotNull Class<? extends K> derivedKeyType, @NotNull K derivedKey, List<E> data) {
        super(elementType, derivedKeyType, derivedKey, data);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<? extends ListKey<E, K>> getKeyType() {
        return (Class<ListKey<E, K>>) (Class<?>) ListKey.class;
    }

    @NotNull
    @Override
    protected ListKey<E, K> createKey(K derivedKey) {
        return new ListKey<>(elementType, derivedKeyType, derivedKey);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<? extends List<E>> getDataType() {
        return (Class<List<E>>) (Class<?>) List.class;
    }

    public static <E, K> Builder<E, K> builder() {
        return new Builder<>();
    }

    public static class Builder<E, K>
            extends MutableTypeDerivedKeyCollection.Builder<Builder<E, K>, E, ListKey<E, K>, K, List<E>> {

        @Override
        public MutableTypeDerivedKeyList<E, K> build() {
            return new MutableTypeDerivedKeyList<>(elementType, derivedKeyType, derivedKey, data);
        }

    }

}
