package net.bodz.bas.db.meta;

import java.sql.SQLException;

import net.bodz.bas.db.jdbc.util.IResultColumnMetaData;

public interface IGenericRowType<RowType, Row> {

    int getColumnCount(RowType rowType)
            throws SQLException;

    // 0-based
    IResultColumnMetaData getColumn(RowType rowType, int index)
            throws SQLException;

    Object getColumnData(Row row, int index)
            throws SQLException;

}
