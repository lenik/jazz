package net.bodz.bas.t.catalog;

import java.io.IOException;

import net.bodz.bas.fmt.records.CsvRecords;
import net.bodz.bas.fmt.records.CsvRow;

public class CsvTable
        extends TableParseOptions {

    boolean ignoreEmptyLines = true;
    boolean ignoreComments = false;

    public MutableTable convert(CsvRecords csv)
            throws IOException {
        CsvRow headRow = csv.first();
        int nColumn = headRow.size();

        DefaultTableMetadata columns = new DefaultTableMetadata();
        for (int i = 0; i < nColumn; i++) {
            String name = headRow.get(i);
            if (renameConflictColumns)
                name = Tables.renameConflict(columns, name, renameTryMax);
            columns.addNewColumn(name).setJavaClass(String.class);
        }

        MutableTable table = new MutableTable(columns);
        int rowIndex = 0;
        for (CsvRow csvRow : csv) {
            String rawLine = csvRow.getRawLine().trim();
            if (ignoreEmptyLines && rawLine.isEmpty())
                continue;
            if (ignoreComments && rawLine.startsWith("#"))
                continue;

            if (rowIndex++ == 0)
                continue;

            IMutableRow row = table.addNewRow();
            for (int iCol = 0; iCol < nColumn; iCol++) {
                String cell = csvRow.get(iCol);
                if (trimText) {
                    cell = trimText(cell);
                }
                row.addNewCell().setData(cell);
            }
        }

        if (autoType) {
        }

        return table;
    }

}
