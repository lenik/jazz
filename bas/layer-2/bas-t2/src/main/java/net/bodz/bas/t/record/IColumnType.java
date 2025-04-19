package net.bodz.bas.t.record;

import java.lang.reflect.Method;

import net.bodz.bas.meta.decl.NotNull;

public interface IColumnType<T, E>
        extends IColumnGetter<T, E>,
                IColumnSetter<T, E> {

    @NotNull
    String getName();

    String getDescription();

    @NotNull
    Class<E> getColumnType();

    @Override
    default Method getMethod() {
        return null;
    }

}
