package net.bodz.bas.t.catalog.poi;

import javax.xml.stream.XMLStreamException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.xssf.usermodel.XSSFCell;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.filetype.excel.ExcelParseOptions;
import net.bodz.bas.filetype.excel.ExcelUtil;
import net.bodz.bas.filetype.excel.ss.SsGroup;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.catalog.MutableCell;

public class SheetCell
        extends MutableCell
        implements
            ISheetCell,
            IXmlForm {

    static final Logger logger = LoggerFactory.getLogger(SheetCell.class);

    SsGroup ss = new SsGroup();

    CellType type = CellType._NONE;
    String text;

    public SheetCell(SheetRow row, Class<?> cellType) {
        super(row, cellType);
    }

    public SheetCell(SheetRow row, int columnIndex) {
        super(row, columnIndex);
    }

    public SheetCell(SheetRow row, String columnName) {
        super(row, columnName);
    }

    public SheetCell(SheetRow row) {
        super(row);
    }

    @Override
    public SheetRow getRow() {
        return (SheetRow) super.getRow();
    }

    @Override
    public SheetColumn getMetadata() {
        return (SheetColumn) super.getMetadata();
    }

    @Override
    public SheetColumn getColumn() {
        return (SheetColumn) super.getMetadata();
    }

    @Override
    public SheetTable getTable() {
        return getRow().getTable();
    }

    @Override
    public SheetTable getSheet() {
        return getRow().getTable();
    }

    @Override
    public SheetBook getBook() {
        return getRow().getSheet().getBook();
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
    }

//    @Override
//    public void setData(Object data) {
//        super.setData(data);
//        if (data == null)
//            text = null;
//        else
//            text = data.toString();
//    }
//
    public String getText() {
        return text;
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException {
        out.beginElement("Cell");
        String chars = null;
        if (text != null)
            chars = text;
        else {
            Object data = getData();
            if (data != null)
                chars = data.toString();
        }
        if (chars != null)
            out.writeCharacters(chars);
        out.endElement();
    }

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
        // ISheetColumn column = getColumn();
        String textContent = element.getTextContent();
        // parse?
        setData(textContent);
    }

    public void readObject(Cell poiCell, ExcelParseOptions options) {
        if (poiCell == null)
            throw new NullPointerException("poiCell");

        type = poiCell.getCellType();
        // text = pCell.toString();
        // ExcelUtil.getCellValue();
        text = ExcelUtil.getCellText(poiCell);

        if (options.useRawText) {
            if (poiCell instanceof XSSFCell) {
                XSSFCell xssfCell = (XSSFCell) poiCell;
                text = xssfCell.getRawValue();
            }
        }

        Object data = null;
        switch (type) {
        case BOOLEAN:
            data = poiCell.getBooleanCellValue();
            break;

        case ERROR:
            byte errorByte = poiCell.getErrorCellValue();
            FormulaError error = FormulaError.forInt(errorByte);
            data = error;
            break;

        case FORMULA:
            CellValue val = getBook().evaluator.evaluate(poiCell);
            readObjectFromEvaluated(val);
            break;

        case NUMERIC:
            data = poiCell.getNumericCellValue();
            break;

        case BLANK:
        case STRING:
            data = poiCell.getStringCellValue();
            break;

        default:
            logger.error("invalid cell type: " + type);
            data = null;
        }
        setData(data);
    }

    public void readObjectFromEvaluated(CellValue pCellVal) {
        type = pCellVal.getCellType();
        text = pCellVal.formatAsString();
        Object data = null;
        switch (type) {
        case BOOLEAN:
            data = pCellVal.getBooleanValue();
            break;

        case ERROR:
            byte errorByte = pCellVal.getErrorValue();
            FormulaError error = FormulaError.forInt(errorByte);
            data = error;
            break;

        case NUMERIC:
            data = pCellVal.getNumberValue();
            break;

        case BLANK:
        case STRING:
            data = pCellVal.getStringValue();
            break;

        default:
            logger.error("invalid cell value type: " + type);
            data = null;
        }
        setData(data);
    }

    @Override
    public String toString() {
        Object data = getData();
        return data == null ? "(n/a)" : data.toString();
    }

}