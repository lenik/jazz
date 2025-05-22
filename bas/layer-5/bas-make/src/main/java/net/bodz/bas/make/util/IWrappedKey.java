package net.bodz.bas.make.util;

import net.bodz.bas.meta.decl.NotNull;

public interface IWrappedKey<K> {

    @NotNull
    K getWrappedKey();

}
