package net.bodz.bas.t.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IMutableRow
        extends
            IRow {

    void setCellData(int index, Object o);

    default void setCellData(String name, Object o) {
        int index = getColumnIndex(name);
        setCellData(index, o);
    }

    @Override
    IMutableCell getCell(int columnIndex);

    @Override
    default IMutableCell getCell(String columnName) {
        int index = getColumnIndex(columnName);
        return getCell(index);
    }

    void setCell(int columnIndex, IMutableCell cell);

    default void setCell(String columnName, IMutableCell cell) {
        int index = getColumnIndex(columnName);
        setCell(index, cell);
    }

    default IMutableCell newCell() {
        MutableCell cell = new MutableCell(this);
        addCell(cell);
        return cell;
    }

    default IMutableCell newCell(int columnIndex) {
        MutableCell cell = new MutableCell(this);
        addCell(columnIndex, cell);
        return cell;
    }

    default void addCell(IMutableCell cell) {
        addCell(getCellCount(), cell);
    }

    void addCell(int columnIndex, IMutableCell cell);

    void removeCell(IMutableCell cell);

    void removeCell(int columnIndex);

    default void readObject(ResultSet rs)
            throws SQLException {
        IRowSetMetadata metadata = getRowSet().getMetadata();
        int cc = metadata.getColumnCount();
        for (int i = 0; i < cc; i++) {
            Object cell = rs.getObject(i + 1);
            this.setCellData(i, cell);
        }
    }

}
