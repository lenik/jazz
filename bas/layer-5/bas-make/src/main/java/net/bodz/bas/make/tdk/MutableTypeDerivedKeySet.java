package net.bodz.bas.make.tdk;

import java.util.Set;

import net.bodz.bas.make.util.SetKey;
import net.bodz.bas.meta.decl.NotNull;

public class MutableTypeDerivedKeySet<K, E>
        extends MutableTypeDerivedKeyCollection<SetKey<E, K>, K, Set<E>, E>
        implements ITypeDerivedKeySet<K, E> {

    public MutableTypeDerivedKeySet(@NotNull Class<? extends E> elementType, @NotNull K derivedKey) {
        super(elementType, derivedKey, null);
    }

    public MutableTypeDerivedKeySet(@NotNull Class<? extends E> elementType, @NotNull K derivedKey, Set<E> data) {
        super(elementType, derivedKey, data);
    }

    public MutableTypeDerivedKeySet(@NotNull Class<? extends E> elementType, @NotNull Class<? extends K> derivedKeyType, @NotNull K derivedKey, Set<E> data) {
        super(elementType, derivedKeyType, derivedKey, data);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<? extends SetKey<E, K>> getKeyType() {
        return (Class<? extends SetKey<E, K>>) (Class<?>) SetKey.class;
    }

    @Override
    protected SetKey<E, K> createKey(K derivedKey) {
        return new SetKey<>(elementType, derivedKeyType, derivedKey);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<? extends Set<E>> getDataType() {
        return (Class<? extends Set<E>>) (Class<?>) Set.class;
    }

    public static <K, E> MutableTypeDerivedKeySet.Builder<K, E> builder() {
        return new MutableTypeDerivedKeySet.Builder<>();
    }

    public static class Builder<K, E>
            extends MutableTypeDerivedKeyCollection.Builder<MutableTypeDerivedKeySet.Builder<K, E>, SetKey<E, K>, K, Set<E>, E> {

        @Override
        public MutableTypeDerivedKeySet<K, E> build() {
            return new MutableTypeDerivedKeySet<>(elementType, derivedKeyType, derivedKey, data);
        }

    }

}
