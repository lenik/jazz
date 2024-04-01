package net.bodz.bas.t.catalog.poi;

import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.w3c.dom.Element;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.filetype.excel.ExcelParseOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.t.catalog.IMutableRow;
import net.bodz.bas.t.catalog.IRow;
import net.bodz.bas.t.catalog.ISchema;
import net.bodz.bas.t.catalog.MutableTable;

public class SheetTable
        extends MutableTable
        implements
            ISheetTable,
            ISheet,
            IXmlForm {

    SheetTableMetadata metadata;
    String sheetName;

    public SheetTable(SheetBook workbook) {
        this(workbook, new SheetTableMetadata());
    }

    public SheetTable(SheetBook workbook, SheetTableMetadata metadata) {
        super();
        setParent(workbook);
        this.metadata = metadata;
    }

    @Override
    public String getSheetName() {
        return sheetName;
    }

    @Override
    public SheetTableMetadata getMetadata() {
        return metadata;
    }

    @Override
    public SheetBook getParent() {
        return (SheetBook) super.getParent();
    }

    @Override
    public void setParent(ISchema parent) {
        SheetBook book = Nullables.upCast(parent, SheetBook.class, "parent");
        super.setParent(book);
    }

    public void setParent(SheetBook book) {
        super.setParent(book);
    }

    @Override
    public SheetTable getTable() {
        return this;
    }

    @Override
    public SheetTable getSheet() {
        return this;
    }

    @Override
    public SheetBook getBook() {
        return getParent();
    }

    public List<? extends SheetColumn> getColumns() {
        return metadata.getColumns();
    }

    @Override
    public SheetRow getRow(int index, IRow fallback) {
        return (SheetRow) super.getRow(index, fallback);
    }

    @Override
    public void setRow(int rowIndex, IMutableRow row) {
        SheetRow sheetRow = Nullables.upCast(row, SheetRow.class, "row");
        super.setRow(rowIndex, sheetRow);
    }

    public void setRow(int rowIndex, SheetRow row) {
        super.setRow(rowIndex, row);
    }

    @Override
    protected SheetRow createRow() {
        return new SheetRow(this);
    }

    @Override
    public SheetRow newRow(int rowIndex) {
        return (SheetRow) super.newRow(rowIndex);
    }

    @Override
    public void addRow(int rowIndex, IMutableRow row) {
        SheetRow sheetRow = Nullables.upCast(row, SheetRow.class, "row");
        super.addRow(rowIndex, sheetRow);
    }

    public void addRow(int rowIndex, SheetRow sheetRow) {
        super.addRow(rowIndex, sheetRow);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<? extends SheetRow> getRows() {
        return (List<? extends SheetRow>) super.getRows();
    }

    @Override
    public SheetRow getRow(int rowIndex) {
        return (SheetRow) super.getRow(rowIndex);
    }

    public void readObject(Sheet poiSheet, ExcelParseOptions options) {
        sheetName = poiSheet.getSheetName();
        int first = poiSheet.getFirstRowNum();
        int last = poiSheet.getLastRowNum();

        for (int rowIndex = first; rowIndex <= last; rowIndex++) {
            if (rowIndex == -1)
                continue;
            SheetRow row = new SheetRow(this, rowIndex);
            Row pRow = poiSheet.getRow(rowIndex);
            row.readObject(pRow, options);
            rows.add(row);
        }
    }

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
        sheetName = element.getAttribute("ss:Name");

        for (Element child : dom.childElements(element)) {
            switch (child.getTagName()) {
            case "Table":
                readObject(child);
                break;

            case "Names":
                break;
            }
        }
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException {
        out.beginElement("Worksheet");
        out.attribute("xmlns:ss", "dummy");
        out.attribute("ss:Name", sheetName);

        out.beginElement("Table");
        for (SheetColumn column : getColumns())
            column.writeObject(out);
        for (SheetRow row : getRows())
            row.writeObject(out);
        out.endElement();

        out.endElement();
    }

}
