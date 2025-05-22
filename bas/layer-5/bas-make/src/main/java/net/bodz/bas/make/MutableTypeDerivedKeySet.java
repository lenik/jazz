package net.bodz.bas.make;

import java.util.Set;

import net.bodz.bas.make.util.SetKey;
import net.bodz.bas.meta.decl.NotNull;

public class MutableTypeDerivedKeySet<K, E>
        extends MutableTypeDerivedKeyCollection<SetKey<K, E>, K, Set<E>, E>
        implements ITypeDerivedKeySet<K, E> {

    public MutableTypeDerivedKeySet(@NotNull Class<E> elementType, @NotNull K wrappedKey, Set<E> data) {
        super(elementType, wrappedKey, data);
    }

    public MutableTypeDerivedKeySet(@NotNull Class<E> elementType, @NotNull Class<K> wrappedKeyType, @NotNull K wrappedKey, Set<E> data) {
        super(elementType, wrappedKeyType, wrappedKey, data);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<SetKey<K, E>> getKeyType() {
        return (Class<SetKey<K, E>>) (Class<?>) SetKey.class;
    }

    @Override
    protected SetKey<K, E> createKey(K derivedKey) {
        return new SetKey<>(elementType, derivedKey);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Class<Set<E>> getDataType() {
        return (Class<Set<E>>) (Class<?>) Set.class;
    }

}
