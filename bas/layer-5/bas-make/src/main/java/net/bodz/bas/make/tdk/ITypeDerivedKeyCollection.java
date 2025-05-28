package net.bodz.bas.make.tdk;

import java.util.Collection;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.util.ICollectionKey;
import net.bodz.bas.meta.decl.NotNull;

public interface ITypeDerivedKeyCollection<CK extends ICollectionKey<E, K>, K, C extends Collection<E>, E>
        extends IKeyData<CK, C>,
                ITypeDerivedKey<E, K>,
                Iterable<E> {

    @NotNull
    default Class<? extends E> getElementType() {
        return getKey().getElementType();
    }

    @NotNull
    @Override
    default Class<? extends E> getDerivedFromType() {
        return getElementType();
    }

    @NotNull
    @Override
    Class<? extends K> getDerivedKeyType();

    @NotNull
    @Override
    default K getDerivedKey() {
        return getKey().getWrappedKey();
    }

}
