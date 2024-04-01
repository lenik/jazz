package net.bodz.bas.t.catalog;

import java.util.Collection;

public interface IRowSet {

    String K_METADATA = "metadata";
    String K_ROWS = "rows";

    IRowSetMetadata getMetadata();

    Collection<? extends IRow> getRows();

    int getRowCount();

    IRow getRow(int index);

}
