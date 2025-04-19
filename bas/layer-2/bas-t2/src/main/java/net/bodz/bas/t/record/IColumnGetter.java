package net.bodz.bas.t.record;

import java.lang.reflect.Method;

public interface IColumnGetter<T, E> {

    E get(T context);

    default boolean hasValue(T context) {
        return get(context) != null;
    }

    default boolean isEmpty(T context) {
        return !hasValue(context);
    }

    default boolean isNotEmpty(T context) {
        return hasValue(context);
    }

    default Method getMethod() {
        return null;
    }

}
