package net.bodz.bas.t.catalog.poi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.xml.stream.XMLStreamException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.w3c.dom.Element;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.filetype.excel.ExcelParseOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.catalog.IMutableCell;
import net.bodz.bas.t.catalog.MutableRow;
import net.bodz.bas.t.pojo.Pair;

public class SheetRow
        extends MutableRow
        implements
            ISheetRow,
            IXmlForm {

    public SheetRow(SheetTable table) {
        super(table);
    }

    @Override
    public SheetTable getTable() {
        return (SheetTable) super.getTable();
    }

    @Override
    public SheetTable getSheet() {
        return getTable();
    }

    @Override
    public SheetBook getBook() {
        return getSheet().getBook();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<? extends SheetCell> getCells() {
        return (List<? extends SheetCell>) super.getCells();
    }

    @Override
    public SheetCell getCell(int index) {
        return (SheetCell) super.getCell(index);
    }

    @Override
    public SheetCell createCell(int columnIndex) {
        return new SheetCell(this, columnIndex);
    }

    @Override
    public SheetCell addNewCell() {
        return (SheetCell) super.addNewCell();
    }

    @Override
    public SheetCell addNewCell(int columnIndex) {
        return (SheetCell) super.addNewCell(columnIndex);
    }

    @Override
    public void addCell(IMutableCell cell) {
        SheetCell sheetCell = Nullables.upCast(cell, SheetCell.class, "cell");
        super.addCell(sheetCell);
    }

    public Object getData(int column) {
        SheetCell cell = getCell(column);
        if (cell == null)
            return null;
        else
            return cell.getData();
    }

    public String getText(int column) {
        SheetCell cell = getCell(column);
        if (cell == null)
            return null;
        else
            return cell.getText();
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException {
        out.beginElement("Row");
        for (SheetCell cell : getCells())
            cell.writeObject(out);
        out.endElement();
    }

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
        for (Element child : dom.childElements(element)) {
            switch (child.getTagName()) {
            case "Cell":
                SheetCell cell = this.addNewCell();
                cell.readObject(child);
                break;
            }
        }
    }

    public void readObject(Row pRow, ExcelParseOptions options) {
        int first = pRow.getFirstCellNum();
        int last = pRow.getLastCellNum();
        for (int iCell = first; iCell < last; iCell++) {
            if (iCell == -1)
                continue;
            SheetCell cell = addNewCell();
            Cell pCell = pRow.getCell(iCell);
            if (pCell != null)
                cell.readObject(pCell, options);
        }
    }

    @Override
    public String toString() {
        return getCells().toString();
    }

    public Map<String, Integer> toMap(SortOrder order) {
        return toMap(order, null, null);
    }

    public Map<String, Integer> toMap(SortOrder order, Function<String, String> norm, Predicate<String> predicate) {
        if (norm == null)
            norm = String::trim;
        if (predicate == null)
            predicate = ((Predicate<String>) String::isEmpty).negate();
        Map<String, Integer> map = order.newMap();
        int nCell = getCellCount();
        for (int iCell = 0; iCell < nCell; iCell++) {
            SheetCell cell = getCell(iCell);
            String cellText = cell.getText();
            if (cellText == null)
                continue;
            cellText = norm.apply(cellText);
            if (! predicate.test(cellText))
                continue;
            map.put(cellText, iCell);
        }
        return map;
    }

    public List<Pair<Integer, String>> toPairs() {
        return toPairs((String text) -> ! text.isEmpty());
    }

    public List<Pair<Integer, String>> toPairs(Predicate<String> predicate) {
        List<Pair<Integer, String>> pairs = new ArrayList<>();
        int nCell = getCellCount();
        for (int iCell = 0; iCell < nCell; iCell++) {
            SheetCell cell = getCell(iCell);
            String cellText = cell.getText();
            if (cellText == null)
                continue;
            cellText = cellText.trim();
            if (! predicate.test(cellText))
                continue;
            pairs.add(Pair.of(iCell, cellText));
        }
        return pairs;
    }

}
