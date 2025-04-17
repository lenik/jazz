package net.bodz.bas.t.record;

public interface IColumnType<T, E>
        extends IColumnGetter<T, E>,
                IColumnSetter<T, E> {

    Class<E> getColumnType();

}
