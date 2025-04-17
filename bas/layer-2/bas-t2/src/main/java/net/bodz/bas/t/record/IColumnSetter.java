package net.bodz.bas.t.record;

public interface IColumnSetter<T, E> {

    void set(T context, E value);

    default void remove(T context) {
        set(context, null);
    }

}
