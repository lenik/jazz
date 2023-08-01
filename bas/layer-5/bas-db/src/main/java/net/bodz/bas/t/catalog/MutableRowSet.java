package net.bodz.bas.t.catalog;

import java.util.List;

/**
 * Use {@link RowList} instead.
 */
@Deprecated
public class MutableRowSet
        extends RowList {

    public MutableRowSet() {
        super();
    }

    public MutableRowSet(IRowSetMetadata metadata, List<IMutableRow> rows) {
        super(metadata, rows);
    }

    public MutableRowSet(IRowSetMetadata metadata) {
        super(metadata);
    }

}
