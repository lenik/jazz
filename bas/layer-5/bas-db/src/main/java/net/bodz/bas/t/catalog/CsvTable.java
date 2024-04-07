package net.bodz.bas.t.catalog;

import java.io.IOException;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.fmt.records.CsvRecords;
import net.bodz.bas.fmt.records.CsvRow;

public class CsvTable {

    boolean ignoreEmptyLines = true;
    boolean ignoreComments = false;

    boolean renameConflictColumns;
    int renameTryMax = 100;

    boolean autoType;
    int maxSamples; // scan from the top

    public MutableTable convert(CsvRecords csv)
            throws IOException {
        CsvRow headRow = csv.first();
        int nColumn = headRow.size();

        DefaultTableMetadata columns = new DefaultTableMetadata();
        for (int i = 0; i < nColumn; i++) {
            String name = headRow.get(i);
            if (renameConflictColumns)
                name = renameConflict(columns, name, renameTryMax);
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
                row.addNewCell().setData(cell);
            }
        }

        if (autoType) {
        }

        return table;
    }

    public static String renameConflict(IRowSetMetadata columns, String name, int max) {
        if (columns.getColumn(name) == null)
            return name;
        for (int i = 1; i <= max; i++) {
            String newName = name + "_" + i;
            IColumnMetadata existing = columns.getColumn(newName);
            if (existing == null)
                return newName;
        }
        throw new DuplicatedKeyException("column duplicated: " + name);
    }

}
