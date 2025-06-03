package net.bodz.bas.make.tdk;

import net.bodz.bas.meta.decl.NotNull;

public interface ITypeDerivedKey<T, K> {

    @NotNull
    Class<? extends T> getDerivedFromType();

    @NotNull
    Class<? extends K> getDerivedKeyType();

    @NotNull
    K getDerivedKey();

    default String getKeyString() {
        return getDerivedFromType() + " :: " + getDerivedKey();
    }

}
