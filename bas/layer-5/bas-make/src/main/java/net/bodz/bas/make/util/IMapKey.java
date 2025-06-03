package net.bodz.bas.make.util;

import net.bodz.bas.meta.decl.NotNull;

public interface IMapKey<E, EK, K>
        extends IWrappedKey<K> {

    @NotNull
    Class<? extends EK> getElementKeyType();

    @NotNull
    Class<? extends E> getElementValueType();

}
