package net.bodz.bas.doc.node.conv;

import java.util.function.Predicate;

import org.apache.poi.xwpf.usermodel.*;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.doc.node.*;
import net.bodz.bas.doc.node.Document;
import net.bodz.bas.doc.node.util.IListStyle;
import net.bodz.bas.doc.property.HorizAlignment;
import net.bodz.bas.doc.word.xwpf.IXwpfNode;
import net.bodz.bas.doc.word.xwpf.XwpfDocNode;
import net.bodz.bas.doc.word.xwpf.XwpfRunNode;
import net.bodz.bas.doc.word.xwpf.XwpfTableCellNode;
import net.bodz.bas.doc.word.xwpf.XwpfTableNode;
import net.bodz.bas.doc.word.xwpf.XwpfTableRowNode;
import net.bodz.bas.io.res.builtin.InputStreamSource;

public class StrictWordConverter
        extends AbstractXwpfConverter {

    public StrictWordConverter(XWPFDocument _document) {
        super(_document);
    }

//    @Override
//    public void beginNode(IDocNode node) {
//        if (node.isPar()) {
//            XWPFParagraph _par = _document.createParagraph();
//            n_ptr = new XwpfParNode(_par);
//        }
//        if (node.isRun()) {
//            IXwpfNode n_par = n_ptr.closest(XwpfPredicates.PAR);
//            XWPFParagraph _par = (XWPFParagraph) n_par.getElement();
//            XWPFRun _run = _par.createRun();
//            n_ptr = new XwpfRunNode(_run);
//        }
//    }
//
//    @Override
//    public void endNode(IDocNode node) {
//        if (node.isPar() || node.isRun())
//            n_ptr = n_ptr.getParent();
//    }

    @Override
    public void document(Document doc) {
        String title = doc.title.getText();
        if (!Nullables.isEmpty(title)) {
            _document.getProperties().getCoreProperties().setTitle(title);
        }
        scope(() -> new XwpfDocNode(_document), doc);
    }

    @Override
    public void part(Part part, int index) {
        // reserved # for title.
        String title = part.title.getText();
        if (Nullables.isEmpty(title)) {
            // generate default title: "Chapter Noname"
            String levelName = part.getLevel().name();
            title = levelName + " Noname";
        }

        XWPFParagraph headerPar = _document.createParagraph();

        String headerStyleId = part.title.getStyleClass();
        if (headerStyleId == null)
            headerStyleId = "Heading" + part.getLevel().level;
        headerPar.setStyle(headerStyleId);

        headerPar.createRun().setText(title);

        part.internalAccept(this);
    }

    @Override
    public void hr(Hr hr) {
        XWPFParagraph hrPar = _document.createParagraph();
        hrPar.setBorderBottom(Borders.SINGLE);
    }

    @Override
    public void list(ListPar list) {
        scope(() -> n_ptr.getDocument(), list);
    }

    @Override
    public void listItem(IPar par, int index, int itemIndex) {
        if (par.isListItem()) {
            ListItem item = (ListItem) par;
            ListPar list = item.getParent();
            IListStyle listStyle = list.getListStyle();

        } else {
            par.accept(this);
        }
    }

    @Override
    public void table(Table table) {
        scope(() -> new XwpfTableNode(_document.createTable()), //
                (XwpfTableNode n_table) -> {
                    // n_table.attribute...
                }, table);
    }

    @Override
    public void tableRow(TableRow row, int index) {
        row.internalAccept(this);

        if (index == 0) {
            Table table = row.getParent();
            TableRow topRow = table.rows.get(0);
            int ccMax = table.getMaxColumnCount();

            StringBuilder alignPatterns = new StringBuilder(ccMax * 10);
            alignPatterns.append("| ");
            int ccTop = topRow.cells.size();
            for (int i = 0; i < ccMax; i++) {
                HorizAlignment alignment = HorizAlignment.LEFT;
                if (i < ccTop) {
                    TableCell cell = topRow.cells.get(i);
                    alignment = cell.getAlignment();
                }
            }
        }
    }

    @Override
    public void tableCell(TableCell cell, int index) {
        XwpfTableRowNode n_row = n_ptr.closest(xp.TABLE_ROW);
        XWPFTableRow _row = n_row.getElement();
        XWPFTableCell _cell = _row.createCell();
        scope(() -> new XwpfTableCellNode(n_ptr, _cell), cell);
    }

    @Override
    public void fontEnv(FontEnv fontEnv) {
        fontEnv.internalAccept(this);
    }

    void ensure(Predicate<IXwpfNode> predicate) {
        if (!predicate.test(n_ptr))
            throw new IllegalStateException(predicate.toString());
    }

    @Override
    public void fontStyleEnv(FontStyleEnv fontStyleEnv) {
        XWPFRun _run;
        switch (n_ptr.getType()) {
        case TABLE_CELL:
            XWPFTableCell _cell = (XWPFTableCell) n_ptr.getElement();
            _run = _cell.addParagraph().createRun();
            break;

        case PARAGRAPH:
            XWPFParagraph _par = (XWPFParagraph) n_ptr.getElement();
            _run = _par.createRun();
            break;

        case RUN:
            _run = (XWPFRun) n_ptr.getElement();
            break;

        default:
            throw new UnsupportedOperationException();
        }

        if (fontStyleEnv.isEffectiveBold())
            _run.setBold(true);
        if (fontStyleEnv.isEffectiveItalic())
            _run.setItalic(true);
        if (fontStyleEnv.isEffectiveStrikeline())
            _run.setStrikeThrough(true);
        if (fontStyleEnv.isEffectiveUnderline())
            _run.setUnderline(UnderlinePatterns.SINGLE);

        fontStyleEnv.internalAccept(this);
    }

    @Override
    public void image(Image image) {
        String href = image.getHref();
        String description = image.getDescription();
        InputStreamSource data = image.getData();
    }

    @Override
    public void chars(String s) {
        if (!n_ptr.isRun())
            throw new IllegalStateException("not in a text run.");
        XwpfRunNode r = (XwpfRunNode) n_ptr;
        XWPFRun _run = r.getElement();
        _run.setText(s);
        return;
    }

}
