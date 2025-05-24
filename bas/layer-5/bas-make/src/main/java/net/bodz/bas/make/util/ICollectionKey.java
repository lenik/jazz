package net.bodz.bas.make.util;

import net.bodz.bas.meta.decl.NotNull;

public interface ICollectionKey<E, K>
        extends IWrappedKey<K> {

    @NotNull
    Class<? extends E> getElementType();

}
