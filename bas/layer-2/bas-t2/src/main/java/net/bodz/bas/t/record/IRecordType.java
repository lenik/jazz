package net.bodz.bas.t.record;

import java.util.List;

public interface IRecordType<T> {

    List<? extends IColumnType<T, ?>> getColumns();

    @SuppressWarnings("unchecked")
    default <E> IColumnType<T, E> getColumn(int index) {
        return (IColumnType<T, E>) getColumns().get(index);
    }

}
