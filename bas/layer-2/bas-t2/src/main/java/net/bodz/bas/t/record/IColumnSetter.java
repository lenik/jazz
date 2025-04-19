package net.bodz.bas.t.record;

import java.lang.reflect.Method;

public interface IColumnSetter<T, E> {

    void set(T context, E value);

    default void remove(T context) {
        set(context, null);
    }

    default Method getMethod() {
        return null;
    }

}
