package net.bodz.bas.t.catalog.poi;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.w3c.dom.Element;

import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.filetype.excel.ExcelParseOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.t.catalog.MutableSchema;

public class SheetBook
        extends MutableSchema
        implements
            ISheetBook,
            IXmlForm {

    SheetDocProperties documentProperties = new SheetDocProperties();
    List<SheetTable> sheets = new ArrayList<>();

    FormulaEvaluator evaluator;

    public List<SheetTable> getSheets() {
        return sheets;
    }

    public int getSheetCount() {
        return sheets.size();
    }

    public SheetTable getSheet(int i) {
        return sheets.get(i);
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException {
        out.beginElement("Workbook");
        documentProperties.writeObject(out);
        for (SheetTable sheet : sheets)
            sheet.writeObject(out);
        out.endElement();
    }

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
        for (Element child : dom.childElements(element)) {
            switch (child.getTagName()) {
            case "DocumentProperties":
                documentProperties.readObject(child);
                break;

            case "Worksheet":
                SheetTable sheet = new SheetTable(this);
                sheet.readObject(child);
                sheets.add(sheet);
                break;

            case "ExcelWorkbook":
            case "Styles":
            default:
                break;

            }
        }
    }

    public void readObject(Workbook poiWorkbook, ExcelParseOptions options) {
        evaluator = poiWorkbook.getCreationHelper().createFormulaEvaluator();
        int nSheet = poiWorkbook.getNumberOfSheets();
        for (int i = 0; i < nSheet; i++) {
            Sheet pSheet = poiWorkbook.getSheetAt(i);
            SheetTable sheet = new SheetTable(this);
            sheet.readObject(pSheet, options);
            sheets.add(sheet);
        }
    }

    public void dump(IPrintOut out) {
        dump(TreeOutImpl.from(out));
    }

    public void dump(ITreeOut out) {
        for (SheetTable sheet : getSheets()) {
            // int nRow = sheet.getRowCount();
            out.println("Sheet: " + sheet.getName());
            out.enter();
            for (SheetRow row : sheet.getTable().getRows()) {
                int w = row.getCellCount();
                for (int i = 0; i < w; i++) {
                    SheetCell cell = row.getCell(i);
                    CellType type = cell.getType();
                    if (type == CellType._NONE)
                        continue;

                    String text = cell.getText();
                    // Object value = cell.getValue();

                    if (i != 0)
                        out.print("\t");
                    if (type == CellType.STRING) {
                        out.print(StringQuote.qqJavaString(text));
                    } else {
                        out.print(type);
                        out.print(":");
                        out.print(text);
                    }
                }
                out.println();
            }
            out.leave();
            out.println();
        }
    }

}
