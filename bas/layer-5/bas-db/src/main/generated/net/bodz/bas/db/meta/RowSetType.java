package net.bodz.bas.db.meta;

import java.sql.SQLException;

import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.IRow;
import net.bodz.bas.t.catalog.IRowSetMetadata;

public class RowSetType
        implements IGenericRowType<IRowSetMetadata, IRow> {

    @Override
    public int getColumnCount(IRowSetMetadata metadata)
            throws SQLException {
        return metadata.getColumnCount();
    }

    @Override
    public IColumnMetadata getColumn(IRowSetMetadata metadata, int index)
            throws SQLException {
        return metadata.getColumn(index);
    }

    @Override
    public Object getColumnData(IRow o, int index)
            throws SQLException {
        return o.getCellData(index);
    }

    public static final RowSetType INSTANCE = new RowSetType();

}
