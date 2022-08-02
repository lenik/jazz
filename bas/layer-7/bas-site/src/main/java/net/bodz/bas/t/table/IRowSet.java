package net.bodz.bas.t.table;

public interface IRowSet
        extends
            Iterable<IRow> {

    String K_METADATA = "metadata";
    String K_ROWS = "rows";

    IRowSetMetadata getMetadata();

    int getRowCount();

    IRow getRow(int index);

}
