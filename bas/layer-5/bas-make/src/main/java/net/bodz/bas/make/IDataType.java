package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public interface IDataType<T> {

    @NotNull
    Class<? extends T> getDataType();

}
