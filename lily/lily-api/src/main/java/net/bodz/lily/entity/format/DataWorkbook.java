package net.bodz.lily.entity.format;

import java.io.IOException;

import org.apache.poi.ss.usermodel.*;

import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.IRow;
import net.bodz.bas.t.catalog.IRowSet;
import net.bodz.bas.t.catalog.IRowSetMetadata;

public class DataWorkbook {

    Workbook workbook;
    Sheet sheet;

    CellStyle headStyle;
    Font headFont;
    CellStyle style;

    boolean autoDetectNumbers = true;

    public DataWorkbook(boolean xssf)
            throws IOException {
        this(WorkbookFactory.create(xssf), true);
    }

    public DataWorkbook(Workbook workbook) {
        this(workbook, false);
    }

    public DataWorkbook(Workbook workbook, boolean createSheet) {
        if (workbook == null)
            throw new NullPointerException("workbook");
        this.workbook = workbook;
        if (createSheet) {
            sheet = workbook.createSheet();
        } else {
            int activeSheetIndex = workbook.getActiveSheetIndex();
            sheet = workbook.getSheetAt(activeSheetIndex);
        }
    }

    public DataWorkbook(Sheet sheet) {
        if (sheet == null)
            throw new NullPointerException("sheet");
        this.workbook = sheet.getWorkbook();
        this.sheet = sheet;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void createStyles() {
        headStyle = workbook.createCellStyle();
        headStyle.setWrapText(true);
        headStyle.setVerticalAlignment(VerticalAlignment.TOP);

        headFont = workbook.createFont();
        headFont.setBold(true);
        headStyle.setFont(headFont);

        style = workbook.createCellStyle();
        style.setWrapText(true);
        style.setVerticalAlignment(VerticalAlignment.TOP);
    }

    public void dumpRowSet(IRowSet rowSet) {
        Row header = sheet.createRow(0);
        if (headStyle != null)
            header.setRowStyle(headStyle);

        IRowSetMetadata metadata = rowSet.getMetadata();
        int nCol = metadata.getColumnCount();
        for (int i = 0; i < nCol; i++) {
            IColumnMetadata column = metadata.getColumn(i);
            String label = column.getLabel();
            if (label == null)
                label = column.getName();

            Cell cell = header.createCell(i);
            if (headStyle != null)
                cell.setCellStyle(headStyle);
            cell.setCellValue(label);
        }

        int rowIndex = 0;
        for (IRow dataRow : rowSet) {
            Row row = sheet.createRow(++rowIndex);
//            row.setHeight((short) -1);
            if (style != null)
                row.setRowStyle(style);

            for (int i = 0; i < nCol; i++) {
                Object value = dataRow.getCellData(i);
                Cell cell = row.createCell(i);
                if (style != null)
                    cell.setCellStyle(style);

                if (value != null) {
                    setCellVar(cell, value);
                }
            } // for column
        } // for row

        if (style != null)
            for (int i = 0; i < nCol; i++)
                sheet.autoSizeColumn(i);
    }

    static void setCellVar(Cell cell, Object var) {
        if (var == null) {
            cell.setBlank();
            return;
        }
        switch (TypeKind.getTypeId(var.getClass())) {
        case TypeId.BOOLEAN:
            cell.setCellValue(((Boolean) var).booleanValue());
            break;
        case TypeId.SHORT:
            cell.setCellValue(((Short) var).shortValue());
            break;

        case TypeId.INTEGER:
            cell.setCellValue(((Integer) var).intValue());
            break;

        case TypeId.LONG:
            cell.setCellValue(((Long) var).longValue());
            break;

        case TypeId.FLOAT:
            cell.setCellValue(((Float) var).floatValue());
            break;

        case TypeId.DOUBLE:
            cell.setCellValue(((Double) var).doubleValue());
            break;

//        case TypeId.BIG_DECIMAL:
//            cell.setCellValue(var.toString());
//            // cell.setCellFormula(var.toString());
//            break;
//
//        case TypeId.BIG_INTEGER:
//            cell.setCellValue(var.toString());
//            // cell.setCellFormula(var.toString());
//            break;

        default:
            String s = var.toString();
            Long _long = parseLongExactly(s);
            if (_long != null) {
                cell.setCellValue(_long);
                break;
            }
            Double _double = parseDoubleExactly(s);
            if (_double != null) {
                cell.setCellValue(_double);
                break;
            }

            cell.setCellValue(var.toString());
        }
    }

    static Long parseLongExactly(String s) {
        try {
            long num = Long.parseLong(s);
            String s2 = String.valueOf(num);
            if (s.equals(s2))
                return num;
        } catch (Exception e) {
        }
        return null;
    }

    static Double parseDoubleExactly(String s) {
        try {
            double num = Double.parseDouble(s);
            String s2 = String.valueOf(num);
            if (s.equals(s2))
                return num;
        } catch (Exception e) {
        }
        return null;
    }

}
