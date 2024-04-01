package net.bodz.bas.t.catalog.poi;

import java.util.List;

public interface ISheetRow {

    int getRowIndex();

    ISheetTable getTable();

    default ISheet getSheet() {
        return getTable().getSheet();
    }

    default ISheetBook getBook() {
        return getSheet().getBook();
    }

    int getCellCount();

    ISheetCell getCell(int cellIndex);

    List<? extends ISheetCell> getCells();

}
