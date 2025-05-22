package net.bodz.bas.make.util;

import net.bodz.bas.meta.decl.NotNull;

public interface IMapKey<K, EK, EV>
        extends IWrappedKey<K> {

    @NotNull
    Class<EK> getElementKeyType();

    @NotNull
    Class<EV> getElementValueType();

}
