package net.bodz.bas.make.util;

import net.bodz.bas.meta.decl.NotNull;

public interface IMapKey<EK, EV, K>
        extends IWrappedKey<K> {

    @NotNull
    Class<? extends EK> getElementKeyType();

    @NotNull
    Class<? extends EV> getElementValueType();

}
