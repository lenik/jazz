package net.bodz.bas.t.table;

public interface IMutableRow
        extends
            IRow {

    void set(int index, Object o);

    default void set(String name, Object o) {
        set(getMetadata(name).getIndex(), o);
    }

}
