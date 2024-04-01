package net.bodz.bas.t.catalog.poi;

public interface ISheetCell {

    ISheetRow getRow();

    ISheetColumn getColumn();

    default int getRowIndex() {
        return getRow().getRowIndex();
    }

    default int getColumnIndex() {
        return getColumn().getColumnIndex();
    }

    default String getColumnName() {
        return getColumn().getColumnName();
    }

    default ISheetTable getTable() {
        return getRow().getTable();
    }

    default ISheet getSheet() {
        return getTable().getSheet();
    }

    default ISheetBook getBook() {
        return getSheet().getBook();
    }

}
