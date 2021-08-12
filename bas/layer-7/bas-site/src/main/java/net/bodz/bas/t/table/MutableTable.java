package net.bodz.bas.t.table;

import java.util.List;

public class MutableTable
        extends RowList {

    public MutableTable(ITableMetadata metadata) {
        super(metadata);
    }

    public MutableTable(ITableMetadata metadata, List<IRow> rows) {
        super(metadata, rows);
    }

    @Override
    public ITableMetadata getMetadata() {
        return (ITableMetadata) super.getMetadata();
    }

    @Override
    protected ITableMetadata newMetadata() {
        return new DefaultTableMetadata();
    }

    @Override
    public String toString() {
        return String.format("[%d] %s", //
                getRowCount(), getMetadata().toString());
    }

}
