package net.bodz.bas.make;

import java.util.Collection;

import net.bodz.bas.make.util.ICollectionKey;
import net.bodz.bas.meta.decl.NotNull;

public interface ITypeDerivedKeyCollection<CK extends ICollectionKey<K, E>, K, C extends Collection<E>, E>
        extends IKeyData<CK, C>,
                ITypeDerivedKey<E, K>,
                Iterable<E> {

    @NotNull
    default Class<E> getElementType() {
        return getKey().getElementType();
    }

    @Override
    default Class<E> getDerivedFromType() {
        return getElementType();
    }

    @Override
    Class<K> getDerivedKeyType();

    @NotNull
    @Override
    default K getDerivedKey() {
        return getKey().getWrappedKey();
    }

}
