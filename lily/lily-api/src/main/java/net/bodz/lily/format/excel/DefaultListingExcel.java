package net.bodz.lily.format.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.site.json.TableOfPathProps;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public class DefaultListingExcel
        extends ExcelBuilder {

    TableOfPathProps tableData;
    IEntityTypeInfo typeInfo;

    public DefaultListingExcel(TableOfPathProps tableData, IEntityTypeInfo typeInfo) {
        this.tableData = tableData;
        this.typeInfo = typeInfo;
    }

    @Override
    protected String getTitle() {
        List<?> list = tableData.getList();
        String dateStr = Dates.ISO_LOCAL_DATE.format(System.currentTimeMillis());
        String typeName = typeInfo.getEntityClass().getSimpleName();
        String title = String.format("%s %s (%d rows)", dateStr, typeName, list.size());
        return title;
    }

    @Override
    protected void buildMainSheet(Sheet sheet)
            throws FormatException {
        List<?> list = tableData.getList();

        // fillTable(sheet, tableData);
        Row header = sheet.createRow(0);
        List<String> columns = tableData.getColumnList();

        int nCol = columns.size();
        for (int i = 0; i < nCol; i++) {
            Cell cell = header.createCell(i);
            String col = columns.get(i);
            cell.setCellValue(col);
        }

        int rowIndex = 0;
        for (Object o : list) {
            List<?> line;
            try {
                line = tableData.convert(o, columns);
            } catch (ReflectiveOperationException e) {
                throw new FormatException("Error convert object to cell array: " + e.getMessage(), e);
            }

            Row row = sheet.createRow(++rowIndex);
            for (int i = 0; i < nCol; i++) {
                Object value = line.get(i);
                Cell cell = row.createCell(i);
                if (value != null)
                    cell.setCellValue(value.toString());
            }
        }
    }

}
