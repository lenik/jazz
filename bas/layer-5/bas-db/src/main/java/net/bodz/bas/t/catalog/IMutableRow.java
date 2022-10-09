package net.bodz.bas.t.catalog;

public interface IMutableRow
        extends
            IRow {

    void set(int index, Object o);

    default void set(String name, Object o) {
        int index = getColumnIndex(name);
        set(index, o);
    }

}
