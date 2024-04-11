package net.bodz.bas.t.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IMutableRow
        extends
            IRow {

    @Override
    List<? extends IMutableCell> getCells();

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

    default void setCell(String columnName, IMutableCell cell) {
        int index = getColumnIndex(columnName);
        setCell(index, cell);
    }

    IMutableCell createCell(int columnIndex);

    default IMutableCell addNewCell() {
        IRowSetMetadata metadata = getRowSet().getMetadata();
        IMutableCell cell;
        if (metadata != null) {
            int columnIndex = getCellCount();
            cell = createCell(columnIndex);
        } else {
            cell = new MutableCell(this);
        }
        addCell(cell);
        return cell;
    }

    default IMutableCell addNewCell(int columnIndex) {
        MutableCell cell = new MutableCell(this, columnIndex);
        setCell(columnIndex, cell);
        return cell;
    }

    void addCell(IMutableCell cell);

    void setCell(int columnIndex, IMutableCell cell);

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
