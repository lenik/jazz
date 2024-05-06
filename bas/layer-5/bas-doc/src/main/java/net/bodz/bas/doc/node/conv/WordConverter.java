package net.bodz.bas.doc.node.conv;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.ooxml.POIXMLProperties.CoreProperties;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.doc.node.*;
import net.bodz.bas.doc.node.util.IListStyle;
import net.bodz.bas.doc.property.Color;
import net.bodz.bas.doc.property.HorizAlignment;
import net.bodz.bas.doc.property.MeasureLength;
import net.bodz.bas.doc.word.DocNum;
import net.bodz.bas.doc.word.INumStyles;
import net.bodz.bas.doc.word.xwpf.IXwHavePars;
import net.bodz.bas.doc.word.xwpf.IXwHaveRuns;
import net.bodz.bas.doc.word.xwpf.IXwNode;
import net.bodz.bas.doc.word.xwpf.XwDocument;
import net.bodz.bas.doc.word.xwpf.XwNodeType;
import net.bodz.bas.doc.word.xwpf.XwPar;
import net.bodz.bas.doc.word.xwpf.XwPredicates;
import net.bodz.bas.doc.word.xwpf.XwRun;
import net.bodz.bas.doc.word.xwpf.XwTable;
import net.bodz.bas.doc.word.xwpf.XwTableCell;
import net.bodz.bas.doc.word.xwpf.XwTableRow;
import net.bodz.bas.doc.word.xwpf.XwUtils;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.stack.ContextStack;
import net.bodz.bas.t.stack.NodePredicates;

public class WordConverter
        extends AbstractDocVisitor
        implements
            INumStyles {

    static final Logger logger = LoggerFactory.getLogger(WordConverter.xp.class);

    protected static class xp
            extends XwPredicates {
    }

    ContextStack<IXwNode> stack = new ContextStack<>();
    XwDocument x_doc;
    XWPFDocument _document;

    ContextStack<TextParHandler> textParHandlerStack = new ContextStack<>();

    public WordConverter(XWPFDocument _doc) {
        this(new XwDocument(_doc));
    }

    public WordConverter(IXwNode stackTop) {
        stack.push(stackTop);
    }

    XwPar getParToAppend() {
        IXwNode top = stack.top();
        if (top.havePars()) {

        }
        return null;
    }

    XwRun getRunToAppend() {
        IXwNode top = stack.top();
        if (top.haveRuns()) {
            IHaveRuns runs = (IHaveRuns) top;
        }
        return null;
    }

    @Override
    public void document(Document doc) {
        IXwNode top = stack.top();
        this.x_doc = (XwDocument) top;
        this._document = x_doc.getElement();

        String title = doc.title.getText();
        String author = doc.author.getText();
        String editor = doc.editor.getText();

        CoreProperties corePr = _document.getProperties().getCoreProperties();
        if (! Nullables.isEmpty(title))
            corePr.setTitle(title);
        if (! Nullables.isEmpty(author))
            corePr.setCreator(author);
        if (! Nullables.isEmpty(editor))
            corePr.setLastModifiedByUser(editor);

        super.document(doc);
    }

    @Override
    public void partGroup(PartGroup partGroup) {
        super.partGroup(partGroup);
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
        if (headerStyleId == null) {
            headerStyleId = x_doc.styles.parStyles.get("heading " + part.getLevel().level);
        }
        headerPar.setStyle(headerStyleId);
        headerPar.createRun().setText(title);

        super.part(part, index);
    }

    @Override
    public void list(ListPar list) {
        if (list.isMultiLevel()) {
            if (list.getLevel() == 0) {
                List<IListStyle> vec = list.getStyleVector();
                list._docNum = x_doc.numbering.compile(vec);
            } else {
                ListPar root = list.getRootLevel();
                list._docNum = root._docNum;
            }
        } else {
            list._docNum = x_doc.numbering.compile(list.getListStyle());
        }
        super.list(list);
    }

    @Override
    public void listItem(ListItem item, int index, int itemIndex) {
        ListPar listPar = item.getParent();
        int level = item.getListLevel();
        BigInteger _docNum = listPar._docNum;

        IXwNode top = stack.top();
        IXwHavePars pars = top.closest(xp.HAVE_PARS);
        XwPar x_par = pars.addPar();
        XWPFParagraph _par = x_par.getElement();
        DocNum docNum = new DocNum(_docNum);
        docNum.apply(_par, level, level == 0 && item.isLast());

        stack.push(x_par);
        textParHandlerStack.push(new ItemTextParHandler());
        super.listItem(item, index, itemIndex);
        textParHandlerStack.pop();
        stack.pop();
    }

    @Override
    public void table(Table table) {
        int columnCount = table.getMaxColumnCount();
        int rowCount = table.getRowCount();
        XWPFTable _table = _document.createTable(rowCount, columnCount);

        String styleName = table.getStyleClass();
        if (styleName != null) {
            String styleId = x_doc.styles.tableStyles.get(styleName);
            if (styleId == null)
                logger.error("Invalid table style name: " + styleName);
            else
                _table.setStyleID(styleId);
        }

        CTTbl tbl = _table.getCTTbl();
        CTTblPr tblPr = tbl.getTblPr();
        CTTblLook look = tblPr.addNewTblLook();
        look.setFirstRow(table.firstRows > 0);
        look.setFirstColumn(table.firstColumns > 0);
        look.setLastRow(table.lastRows > 0);
        look.setLastColumn(table.lastColumns > 0);
        look.setNoHBand(! table.hBands);
        look.setNoVBand(! table.vBands);

        if (styleName != null) {
            tblPr.unsetTblBorders();
            CTTblBorders borders = tblPr.getTblBorders();
            if (borders == null)
                borders = tblPr.addNewTblBorders();
            borderSingleAuto(borders.addNewTop(), 0, 0);
            borderSingleAuto(borders.addNewLeft(), 0, 0);
            borderSingleAuto(borders.addNewBottom(), 0, 0);
            borderSingleAuto(borders.addNewRight(), 0, 0);
            borderSingleAuto(borders.addNewInsideH(), 0, 0);
            borderSingleAuto(borders.addNewInsideV(), 0, 0);
        }

        CTTblGrid tblGrid = tbl.getTblGrid();
        if (tblGrid == null)
            tblGrid = tbl.addNewTblGrid();
        int maxColumnCount = table.getMaxColumnCount();
        for (int iCol = 0; iCol < maxColumnCount; iCol++) {
            CTTblGridCol gridCol = tblGrid.addNewGridCol();
            gridCol.setW(1 * 1440); // 1-inch
        }

        table.updateMergeTos();
        // table.dumpMergeTos(Stdio.cout);

        XwTable x_table = new XwTable(_table);
        stack.push(x_table);
        super.table(table);
        stack.pop();

//        for (int col = 0; col < maxColumnCount; col++) {
//            CTTblWidth tblWidth = CTTblWidth.Factory.newInstance();
//            tblWidth.setW(BigInteger.valueOf(1 * 1440));
//            tblWidth.setType(STTblWidth.DXA);
//            for (int row = 0; row < rowCount; row++) {
//                XWPFTableCell _cell = _table.getRow(row).getCell(col);
//                if (_cell == null)
//                    continue;
//                CTTcPr tcPr = _cell.getCTTc().getTcPr();
//                if (tcPr != null) {
//                    tcPr.setTcW(tblWidth);
//                } else {
//                    tcPr = CTTcPr.Factory.newInstance();
//                    tcPr.setTcW(tblWidth);
//                    _table.getRow(row).getCell(col).getCTTc().setTcPr(tcPr);
//                }
//            }
//        }

    }

    void borderSingleAuto(CTBorder border, int size, int space) {
        border.setVal(STBorder.SINGLE);
        border.setSz(BigInteger.valueOf(size));
        border.setSpace(BigInteger.valueOf(space));
        border.setColor("auto");
    }

    @Override
    public void tableRow(TableRow row, int index) {
        IXwNode top = stack.top();
        Table table = row.getParent();
        XwTable x_table = top.closest(xp.TABLE);

        // XwTableRow x_row = x_table.addRow();
        XWPFTable _table = x_table.getElement();
        XWPFTableRow _row = _table.getRow(index);

        CTRow ctRow = _row.getCtRow();
        CTTrPr trPr = ctRow.isSetTrPr() ? ctRow.getTrPr() : ctRow.addNewTrPr();

        if (table.getRowPosition(index) != RowPosition.BODY)
            trPr.addNewTblHeader();

        XwTableRow x_row = new XwTableRow(_row);

        stack.push(x_row);
        super.tableRow(row, index);
        stack.pop();

        int nCell = _row.getTableCells().size();
        while (x_row.cptr < nCell)
            _row.removeCell(--nCell);

        if (index == 0) {
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
        IXwNode top = stack.top();
        XwTableRow x_row = top.closest(xp.TABLE_ROW);
        if (x_row == null)
            throw new IllegalStateException("without x_row");

        TableCell orig = cell.mergedTo;
        if (orig == null)
            orig = cell;
        else if (orig.getChildIndex() != index) {
            // covered by colspan
            return;
        }

        XWPFTableRow _row = x_row.getElement();
        XWPFTableCell _cell = _row.getCell(x_row.cptr++);
        XwTableCell x_cell = new XwTableCell(_cell);

        if (cell.isHeader()) {
            XwPar x_par = x_cell.getParToAppend();
            XwRun x_run = x_par.getRunToAppend();
            XWPFRun _run = x_run.getElement();
            _run.setBold(true);
        }

        CTTc tc = _cell.getCTTc();
        CTTcPr tcPr = tc.isSetTcPr() ? tc.getTcPr() : tc.addNewTcPr();

        int colSpan = orig.getColumnSpan();
        if (colSpan > 1) {
            CTDecimalNumber gridSpan = tcPr.addNewGridSpan();
            gridSpan.setVal(BigInteger.valueOf(colSpan));

            CTHMerge hMerge = CTHMerge.Factory.newInstance();
            hMerge.setVal(STMerge.RESTART);
            tcPr.setHMerge(hMerge);

        }

        if (cell.getRow() != orig.getRow())
            tcPr.addNewVMerge();
        else {
            int rowSpan = cell.getRowSpan();
            if (rowSpan > 1) {
                CTVMerge vMerge = tcPr.addNewVMerge();
                vMerge.setVal(STMerge.RESTART);
            }
        }

        stack.push(x_cell);
        super.tableCell(cell, index);
        stack.pop();
    }

    @Override
    public void textBox(TextBox textBox) {
        super.textBox(textBox);
    }

    @Override
    public void textPar(TextPar textPar) {
        if (! textParHandlerStack.isEmpty()) {
            textParHandlerStack.top().handleTextPar(textPar);
            return;
        }

        IXwNode top = stack.top();

        IXwHavePars x_pars = top.closest(xp.HAVE_PARS);
        if (x_pars == null)
            throw new IllegalStateException("no context pars: " + top);

        XwPar x_par;
        if (top.getType() == XwNodeType.TABLE_CELL && textPar.getChildIndex() == 0)
            x_par = x_pars.getParToAppend();
        else
            x_par = x_pars.addPar();
        XWPFParagraph _par = x_par.getElement();

        String styleName = textPar.getStyleClass();
        if (styleName != null) {
            String styleId = x_doc.styles.parStyles.get(styleName);
            _par.setStyle(styleId);
        }

        ParagraphAlignment parAlign = XwUtils.convert(textPar.getAlignment());
        _par.setAlignment(parAlign);

        stack.push(x_par);
        super.textPar(textPar);
        stack.pop();
    }

    class ItemTextParHandler
            implements
                TextParHandler {

        int parIndex;

        @Override
        public void handleTextPar(TextPar textPar) {
            IXwNode top = stack.top();
            XwPar x_par = top.closest(xp.PAR);
            if (x_par == null)
                throw new NullPointerException("x_par");
            XWPFParagraph _par = x_par.getElement();

            if (parIndex++ != 0)
                _par.createRun().addBreak();

            stack.push(x_par);
            haveRuns(textPar);
            stack.pop();
        }
    }

    @Override
    public void runGroup(RunGroup runGroup) {
        super.runGroup(runGroup);
    }

    @Override
    public void textRun(TextRun textRun) {
        IXwNode top = stack.top();
        XwRun x_run = top.closest(xp.RUN);
        if (x_run == null) {
            IXwHaveRuns x_runs = top.closest(xp.HAVE_RUNS);
            if (x_runs == null) {
                IXwHavePars x_pars = top.closest(xp.HAVE_PARS);
                x_runs = x_pars.addPar();
            }
            x_run = x_runs.addRun();
        }

        XWPFRun _run = x_run.getElement();

        Color color = textRun.getColor();
        if (color != null)
            _run.setColor(color.getRgbCode());

        CTR r = _run.getCTR();

        Color background = textRun.getBackground();
        if (background != null) {
            CTRPr rPr = r.isSetRPr() ? r.getRPr() : r.addNewRPr();
            CTShd cTShd = rPr.addNewShd();
            cTShd.setVal(STShd.CLEAR);
            // cTShd.setColor("auto");
            cTShd.setFill(background.getRgbCode());
        }

        Color highlightColor = textRun.getHighlight();
        if (highlightColor != null) {
            CTRPr rPr = r.isSetRPr() ? r.getRPr() : r.addNewRPr();
            CTHighlight highlight = rPr.addNewHighlight();
            STHighlightColor.Enum val = STHighlightColor.Enum.forString(highlightColor.getRgbCode());
            highlight.setVal(val);
        }

        for (String s : textRun.textList) {
            if (s == null || s.isEmpty())
                return;
            XwUtils.addPlainText(_run, s);
        }
    }

    @Override
    public void fontEnv(FontEnv fontEnv) {
        IXwNode top = stack.top();
        IXwHaveRuns x_runs;
        if (top.haveRuns())
            x_runs = (IXwHaveRuns) top;
        else if (top.havePars()) {
            IXwHavePars x_pars = (IXwHavePars) top;
            XwPar x_par = x_pars.addPar();
            x_runs = x_par;
        } else if (top.isRun()) {
            XwRun x_run = (XwRun) top;
            XwPar x_parent = x_run.getParent();
            x_runs = x_parent;
        } else
            throw new IllegalStateException();

        List<FontEnv> chain = fontEnv.ancestors(NodePredicates.FONT_ENV);
        String fFamily = null;
        MeasureLength fSize = null;
        for (FontEnv node : chain) {
            if (fFamily == null) {
                String family = node.getFamily();
                if (family != null)
                    fFamily = family;
            }

            if (fSize == null) {
                MeasureLength size = node.getSize();
                if (size != null)
                    fSize = size;
            }
        }

        for (IRun run : fontEnv.runs) {
            XwRun x_run = x_runs.addRun();
            XWPFRun _run = x_run.getElement();
            if (fFamily != null)
                _run.setFontFamily(fFamily);
            if (fSize != null)
                _run.setFontSize(fSize.toTwips());
            stack.push(x_run);
            run.accept(this);
            stack.pop();
        }
    }

    @Override
    public void fontStyleEnv(FontStyleEnv fontStyleEnv) {
        IXwNode top = stack.top();
        IXwHaveRuns x_runs;
        if (top.haveRuns())
            x_runs = (IXwHaveRuns) top;
        else if (top.havePars()) {
            IXwHavePars x_pars = (IXwHavePars) top;
            XwPar x_par = x_pars.addPar();
            x_runs = x_par;
        } else if (top.isRun()) {
            XwRun x_run = (XwRun) top;
            XwPar x_parent = x_run.getParent();
            x_runs = x_parent;
        } else
            throw new IllegalStateException();

        List<FontStyleEnv> chain = fontStyleEnv.ancestors(NodePredicates.FONT_STYLE_ENV);
        boolean bold = false;
        boolean italic = false;
        boolean underline = false;
        boolean strikeline = false;
        for (FontStyleEnv node : chain) {
            bold |= node.isBold();
            italic |= node.isItalic();
            underline |= node.isUnderline();
            strikeline |= node.isStrikeline();
        }

        for (IRun run : fontStyleEnv.runs) {
            XwRun x_run = x_runs.addRun();
            XWPFRun _run = x_run.getElement();
            _run.setBold(bold);
            _run.setItalic(italic);
            _run.setStrikeThrough(strikeline);
            if (underline)
                _run.setUnderline(UnderlinePatterns.SINGLE);

            stack.push(x_run);
            run.accept(this);
            stack.pop();
        }
    }

    @Override
    public void breaker(Breaker breaker) {
        IXwNode top = stack.top();
        XwRun x_run = top.closest(xp.RUN);
        if (x_run == null) {
            IXwHaveRuns runs = top.closest(xp.HAVE_RUNS);
            if (runs != null)
                x_run = runs.addRun();
            else {
                IXwHavePars pars = top.closest(xp.HAVE_PARS);
                x_run = pars.addPar().addRun();
            }
        }
        XWPFRun _run = x_run.getElement();

        switch (breaker.getBreakType()) {
        case PAR:
            IXwHavePars pars = stack.popFor(xp.HAVE_PARS);
            XwPar par = pars.addPar();
            stack.push(par);
            break;

        case LINE:
            _run.addBreak(BreakType.TEXT_WRAPPING);
            break;

        case COLUMN:
            _run.addBreak(BreakType.COLUMN);
            break;

        case PAGE:
//        case EVEN_PAGE:
//        case ODD_PAGE:
            _run.addBreak(BreakType.PAGE);
            break;
        }
    }

    @Override
    public void hr(Hr hr) {
        XWPFParagraph hrPar = _document.createParagraph();
        hrPar.setBorderBottom(Borders.SINGLE);
    }

    @Override
    public void image(Image image) {
        IXwNode top = stack.top();

        // String href = image.getHref();
        // String description = image.getDescription();
        File imageFile = null;
        IStreamInputSource source = image.getSource();
        if (source instanceof FileResource) {
            FileResource fr = (FileResource) source;
            imageFile = fr.getFile();
        }

//        IXwHavePars x_pars = x_ptr.closest(xp.HAVE_PARS);
//        if (x_pars == null)
//            throw new IllegalStateException("no context pars.");
        IXwHaveRuns x_runs = top.closest(xp.HAVE_RUNS);
        if (x_runs == null) {
            IXwHavePars pars = top.closest(xp.HAVE_PARS);
            XwPar par = pars.addPar();
            x_runs = par;
        }

        XwRun run = x_runs.addRun();
        XWPFRun _run = run.getElement();

        String imagePath = imageFile == null ? null : imageFile.getPath();

        double pageWidthPoints = WordUnits.mm2Points(210);
        double pageHeightPoints = WordUnits.mm2Points(297);

        CTSectPr sectPr = _document.getDocument().getBody().getSectPr();
        if (sectPr != null) {
            CTPageSz pageSize = sectPr.getPgSz();
            if (pageSize != null) {
                pageWidthPoints = WordUnits.toPoints(pageSize.getW());
                pageHeightPoints = WordUnits.toPoints(pageSize.getH());
            }
        }

        double widthPoints = 100;
        double heightPoints = 100;
        if (image.getWidth() != null)
            widthPoints = image.getWidth().toPoints(pageWidthPoints);
        if (image.getHeight() != null)
            heightPoints = image.getHeight().toPoints(pageHeightPoints);

        int widthEmu = Units.toEMU(widthPoints);
        int heightEmu = Units.toEMU(heightPoints);

        try {
            FileMagic magic;
            if (imageFile != null) {
                magic = FileMagic.valueOf(imageFile);
            } else {
                try (InputStream in = source.newInputStream()) {
                    magic = FileMagic.valueOf(in);
                }
            }
            PictureType pictureType = PictureType.valueOf(magic);

            try (InputStream in = source.newInputStream()) {

                _run.addPicture(in, pictureType, //
                        imagePath, //
                        widthEmu, //
                        heightEmu);
            } catch (InvalidFormatException e) {
                String message = String.format("Error image format: %s, local file: %s", //
                        e.getMessage(), imageFile);
                _run.setText(message);
            }
        } catch (IOException e) {
            String message = String.format("Error insert image %s: %s", //
                    imageFile, e.getMessage());
            _run.setText(message);
        }
    }

}
