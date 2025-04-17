package net.bodz.bas.t.record;

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

}
