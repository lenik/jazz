package net.bodz.bas.t.catalog;

import net.bodz.bas.fmt.records.CsvRecords;
import net.bodz.bas.fmt.records.CsvRow;

public class TableFn {

    public static MutableTable fromCsv(CsvRecords records) {
        DefaultTableMetadata metadata = null;
        MutableTable table = new MutableTable();
        for (CsvRow csvRow : records) {
            if (metadata == null) {
                metadata = new DefaultTableMetadata();
                for (String cell : csvRow) {
                    DefaultColumnMetadata column = metadata.addNewColumn(cell);
                    column.precision = 1;
                }
            } else {
                IMutableRow row = table.addNewRow();
                for (String csvCell : csvRow) {
                    IMutableCell cell = row.addNewCell();
                    cell.setData(csvCell);

                    DefaultColumnMetadata column = (DefaultColumnMetadata) cell.getMetadata();
                    if (csvCell.length() > column.precision)
                        column.precision = csvCell.length();
                }
            }
        }
        return table;
    }

}
