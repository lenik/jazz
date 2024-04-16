package net.bodz.bas.t.catalog.poi;

import javax.xml.stream.XMLStreamException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.xssf.usermodel.XSSFCell;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.filetype.excel.ExcelParseOptions;
import net.bodz.bas.filetype.excel.ExcelUtil;
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

//    private SsGroup ss;

    private String _text;

    public SheetCell(SheetRow row) {
        super(row);
    }

    public SheetCell(SheetRow row, int columnIndex) {
        super(row, columnIndex);
    }

    public SheetCell(SheetRow row, String columnName) {
        super(row, columnName);
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
        return _text;
    }

    public void setText(String text) {
        if (text != null)
            text = text.intern();
        this._text = text;
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException {
        out.beginElement("Cell");
        String chars = null;
        if (_text != null)
            chars = _text;
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

    public void readObject(Cell pCell, ExcelParseOptions options) {
        if (pCell == null)
            throw new NullPointerException("poiCell");

        CellType type = pCell.getCellType();
        // text = pCell.toString();
        // ExcelUtil.getCellValue();

        String cellText = ExcelUtil.getCellText(pCell);
        if (options.trimText)
            cellText = options.trimText(cellText);
        setText(cellText);

        if (options.useRawText) {
            if (pCell instanceof XSSFCell) {
                XSSFCell xssfCell = (XSSFCell) pCell;
                String rawValue = xssfCell.getRawValue();
                if (options.trimText)
                    rawValue = options.trimText(rawValue);
                setText(rawValue);
            }
        }

        Object data = null;
        switch (type) {
        case BOOLEAN:
            data = pCell.getBooleanCellValue();
            break;

        case ERROR:
            byte errorByte = pCell.getErrorCellValue();
            FormulaError error = FormulaError.forInt(errorByte);
            data = error;
            break;

        case FORMULA:
            CellValue pCellValue = getBook().evaluator.evaluate(pCell);
            readObjectFromEvaluated(pCellValue, pCell, options);
            break;

        case NUMERIC:
            if (DateUtil.isCellDateFormatted(pCell)) {
                data = pCell.getLocalDateTimeCellValue();
            } else {
                data = pCell.getNumericCellValue();
            }
            break;

        case BLANK:
        case STRING:
            String str = pCell.getStringCellValue();
            if (options.trimText)
                str = options.trimText(str);
            data = str;
            break;

        default:
            logger.error("invalid cell type: " + type);
            data = null;
        }
        setData(data);
    }

    public void readObjectFromEvaluated(CellValue pCellVal, Cell pCell, ExcelParseOptions options) {
        CellType type = pCellVal.getCellType();
        setText(pCellVal.formatAsString());
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
            if (DateUtil.isCellDateFormatted(pCell)) {
                data = pCell.getLocalDateTimeCellValue();
            } else
                data = pCellVal.getNumberValue();
            break;

        case BLANK:
        case STRING:
            String str = pCellVal.getStringValue();
            if (options.trimText)
                str = options.trimText(str);
            data = str;
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
