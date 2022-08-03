package net.bodz.bas.t.catalog;

import net.bodz.bas.err.NoSuchKeyException;

public interface IMutableRow
        extends
            IRow {

    void set(int index, Object o);

    default void set(String name, Object o) {
        IColumnMetadata column = getColumn(name);
        if (column == null)
            throw new NoSuchKeyException("No such column " + name);
        set(column.position(), o);
    }

}
