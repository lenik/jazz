package net.bodz.bas.t.catalog;

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
    IMutableCell getCell(String columnName);

    void setCell(int columnIndex);

    void setCell(String columnName);

//    IMutableCell newCell();
//
//    IMutableCell newCell(int columnIndex);
//
//    void addCell(IMutableCell cell);
//
//    void addCell(int columnIndex, IMutableCell cell);
//
//    void removeCell(IMutableCell cell);
//
//    void removeCell(int columnIndex);

}
