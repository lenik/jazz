package net.bodz.bas.doc.word.xwpf;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

import org.apache.poi.xwpf.usermodel.*;

import net.bodz.bas.doc.io.IDocWriter;
import net.bodz.bas.doc.property.ElementType;
import net.bodz.bas.doc.property.MeasureLength;
import net.bodz.bas.doc.property.PartLevel;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.t.variant.MutableVariant;

public class WordWriter
        implements
            IDocWriter<WordWriter> {

    static final double baseSizeInPoints = 12; // 12pt default

    Stack<IXwpfNode> stack = new Stack<>();

    public WordWriter(XWPFDocument document) {
        this(document, null, null);
    }

    public WordWriter(XWPFDocument document, XWPFParagraph paragraph, XWPFRun run) {
        IXwpfNode docNode = new XwpfDocNode(document);
        stack.push(docNode);

        if (run != null && paragraph == null)
            paragraph = (XWPFParagraph) run.getParent();

        if (paragraph != null) {
            IXwpfNode parNode = new XwpfParNode(docNode, paragraph);
            stack.push(parNode);

            if (run != null) {
                IXwpfNode runNode = new XwpfRunNode(parNode, run);
                stack.push(runNode);
            }
        }
    }

    protected IXwpfNode stackTop() {
        if (stack.isEmpty())
            throw new IllegalStateException("no document or closed.");
        return stack.lastElement();
    }

    protected XwpfDocNode resolveDocument() {
        IXwpfNode top = stackTop();
        XwpfDocNode docNode = top.getDocument();
        if (docNode == null)
            throw new IllegalStateException("no context document");
        return docNode;
    }

    protected XwpfParNode resolveParagraph() {
        IXwpfNode top = stackTop();
        XwpfParNode parNode = top.closest(XwpfPredicates.PAR);
        if (parNode == null) {
            XWPFParagraph par = resolveDocument().getElement().createParagraph();
            parNode = new XwpfParNode(top, par);
            stack.push(parNode);
        }
        return parNode;
    }

    protected XwpfRunNode resolveRun() {
        IXwpfNode top = stackTop();
        XwpfRunNode runNode = top.closest(XwpfPredicates.RUN);
        if (runNode == null) {
            XWPFRun run = resolveParagraph().getElement().createRun();
            runNode = new XwpfRunNode(top, run);
            stack.push(runNode);
        }
        return runNode;
    }

    protected XwpfTableNode resolveTable() {
        IXwpfNode top = stackTop();
        XwpfTableNode tableNode = top.closest(XwpfPredicates.TABLE);
        if (tableNode == null)
            throw new IllegalStateException("no context table");
        return tableNode;
    }

    protected XwpfTableRowNode resolveTableRow() {
        IXwpfNode top = stackTop();
        XwpfTableRowNode rowNode = top.closest(XwpfPredicates.TABLE_ROW);
        if (rowNode == null) {
            XwpfTableNode tableNode = resolveTable();
            XWPFTable table = tableNode.getElement();
            int rowCount = table.getNumberOfRows();
            XWPFTableRow lastRow = table.getRow(rowCount - 1);
            rowNode = new XwpfTableRowNode(tableNode, lastRow);
            stack.push(rowNode);
        }
        return rowNode;
    }

    protected XwpfTableCellNode resolveTableCell() {
        IXwpfNode top = stackTop();
        XwpfTableCellNode cellNode = top.closest(XwpfPredicates.TABLE_CELL);
        if (cellNode == null) {
            XwpfTableRowNode rowNode = resolveTableRow();
            XWPFTableRow tableRow = rowNode.getElement();
            // XWPFTable table = tableRow.getTable();
            // int cellCount = table.getNumberOfCells();
            int cellCount = tableRow.getTableICells().size();
            XWPFTableCell lastCell = tableRow.getCell(cellCount - 1);
            cellNode = new XwpfTableCellNode(rowNode, lastCell);
            stack.push(cellNode);
        }
        return cellNode;
    }

    @Override
    public WordWriter begin() {
        return null;
    }

    @Override
    public WordWriter begin(String className) {
        return null;
    }

    @Override
    public WordWriter end() {
        return null;
    }

    @Override
    public WordWriter end(ElementType elementType) {
        return null;
    }

    @Override
    public void endAll() {
    }

    @Override
    public void flush() {
    }

    @Override
    public WordWriter attribute(String name, Object value) {
        MutableVariant val = MutableVariant.wrap(value);
        stackTop().attribute(name, val);
        return this;
    }

    @Override
    public WordWriter put(Object element) {
        return null;
    }

    @Override
    public WordWriter section(PartLevel level, String title) {
        return null;
    }

    @Override
    public WordWriter list(boolean ordered) {
        return null;
    }

    @Override
    public WordWriter item() {
        return null;
    }

    protected TextStyle beginStyle() {
        XwpfRunNode runNode = resolveRun();
        TextStyle style = null; //runNode.beginStyle();
        return style;
    }

    @Override
    public WordWriter b() {
        beginStyle().bold = true;
        return this;
    }

    @Override
    public WordWriter i() {
        beginStyle().italic = true;
        return this;
    }

    @Override
    public WordWriter u() {
        beginStyle().underline = UnderlinePatterns.SINGLE;
        return this;
    }

    @Override
    public WordWriter p() {

        return null;
    }

    @Override
    public WordWriter table() {
        IXwpfNode top = stackTop();
        XwpfDocNode document = top.getDocument();
        XWPFTable table = document.getElement().createTable();
        XwpfTableNode tableNode = new XwpfTableNode(top, table);
        stack.push(tableNode);
        return this;
    }

    @Override
    public WordWriter tr() {
        XwpfTableNode tableNode = resolveTable();
        XWPFTableRow row = tableNode.getElement().createRow();
        XwpfTableRowNode rowNode = new XwpfTableRowNode(row);
        stack.push(rowNode);
        return this;
    }

    @Override
    public WordWriter th() {
        td();
        XwpfTableCellNode cellNode = stackTop().closest(XwpfPredicates.TABLE_CELL);
        XWPFTableCell cell = cellNode.getElement();
        cell.addParagraph();
        // TODO
        return this;
    }

    @Override
    public WordWriter td() {
        XwpfTableRowNode rowNode = resolveTableRow();
        XWPFTableCell cell = rowNode.getElement().createCell();
        XwpfTableCellNode cellNode = new XwpfTableCellNode(cell);
        stack.push(cellNode);

        List<XWPFParagraph> pars = cell.getParagraphs();
        XWPFParagraph par;
        if (!pars.isEmpty())
            par = pars.get(pars.size() - 1);
        else
            par = cell.addParagraph();
        XwpfParNode parNode = new XwpfParNode(cellNode, par);
        stack.push(parNode);

        XWPFRun run = par.createRun();

        return this;
    }

    @Override
    public WordWriter hr() {
        return null;
    }

    protected WordWriter beginStyle(TextStyle style) {
        IXwpfNode top = stackTop();
//        Stack<TextStyle> styleStack = top.getStyleStack();
//        styleStack.push(style);
        return this;
    }

    @Override
    public WordWriter font(String family, MeasureLength size) {
        TextStyle style = new TextStyle();
        style.fontFamily = family;
        style.fontSize = size.getPoints(baseSizeInPoints);
        return beginStyle(style);
    }

    @Override
    public WordWriter fontFamily(String family) {
        TextStyle style = new TextStyle();
        style.fontFamily = family;
        return beginStyle(style);
    }

    @Override
    public WordWriter fontSize(MeasureLength size) {
        TextStyle style = new TextStyle();
        style.fontSize = size.getPoints(baseSizeInPoints);
        return beginStyle(style);
    }

    @Override
    public WordWriter color(String color) {
        TextStyle style = new TextStyle();
        style.color = color;
        return beginStyle(style);
    }

    @Override
    public WordWriter backgroundColor(String color) {
        TextStyle style = new TextStyle();
        return beginStyle(style);
    }

    @Override
    public WordWriter data(Object data) {
        return text(data.toString());
    }

    @Override
    public WordWriter text(String s) {
        throw new NotImplementedException();
    }

    @Override
    public void write(int ch)
            throws IOException {
        String s = new String(new int[] { ch }, 0, 1);
        text(s);
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        String s = new String(chars, off, len);
        text(s);
    }

    @Override
    public boolean isClosed() {
        return stack.isEmpty();
    }

}
