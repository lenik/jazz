package net.bodz.bas.t.model;

import net.bodz.bas.meta.decl.NotNull;

public interface IWrapper<T> {

    @NotNull
    T getWrapped();

}
