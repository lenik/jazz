package net.bodz.bas.make.util;

import net.bodz.bas.meta.decl.NotNull;

public interface ICollectionKey<K, E>
        extends IWrappedKey<K> {

    @NotNull
    Class<E> getElementType();

}
