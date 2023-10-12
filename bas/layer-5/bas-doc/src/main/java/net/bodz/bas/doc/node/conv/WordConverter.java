package net.bodz.bas.doc.node.conv;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.doc.node.*;
import net.bodz.bas.doc.node.Document;
import net.bodz.bas.doc.property.HorizAlignment;
import net.bodz.bas.doc.word.DocNum;
import net.bodz.bas.doc.word.xwpf.*;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.t.stack.NodePredicates;

public class WordConverter
        extends AbstractXwpfConverter {

    Map<String, String> styleMap = new HashMap<>();
    Map<String, String> styleLcMap = new HashMap<>();

    boolean convertParToBreak;
    int convertParIndex = 0;

    public WordConverter(XWPFDocument _document) {
        super(_document);

        XWPFStyles styles = _document.getStyles();
        try {
            CTStyles ctStyles = _document.getStyle();
            for (CTStyle ctStyle : ctStyles.getStyleList()) {
                String styleId = ctStyle.getStyleId();
                XWPFStyle style = styles.getStyle(styleId);
                String styleName = style.getName();
                styleMap.put(styleName, styleId);
                styleLcMap.put(styleName, styleId);
            }
        } catch (XmlException | IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    String getStyleId(String name) {
        String id = styleMap.get(name);
        if (id == null)
            id = styleLcMap.get(name.toLowerCase());
        return id != null ? id : name;
    }

    void ensure(Predicate<IXwNode> predicate) {
        if (!predicate.test(x_ptr))
            throw new IllegalStateException(predicate.toString());
    }

    @Override
    public void document(Document doc) {
        String title = doc.title.getText();
        if (!Nullables.isEmpty(title)) {
            _document.getProperties().getCoreProperties().setTitle(title);
        }
        scope(() -> {
            return new XwDocument(_document);
        }, doc);
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
            headerStyleId = getStyleId("heading " + part.getLevel().level);
        }
        headerPar.setStyle(headerStyleId);

        headerPar.createRun().setText(title);

        part.internalAccept(this);
    }

    @Override
    public void textPar(TextPar textPar) {
        if (convertParToBreak) {
            XwPar x_par = x_ptr.closest(xp.PAR);
            if (x_par == null)
                throw new NullPointerException("x_par");
            XWPFParagraph _par = x_par.getElement();
            if (convertParIndex++ != 0)
                _par.createRun().addBreak();
            scope(() -> {
                return x_par;
            }, textPar);
            return;
        }

        IXwHavePars x_pars = x_ptr.closest(xp.HAVE_PARS);
        if (x_pars == null)
            throw new IllegalStateException("no context pars");

        scope(() -> {
            XwPar x_par = x_pars.addPar();
            XWPFParagraph _par = x_par.getElement();

            String styleName = textPar.getStyleClass();
            if (styleName != null) {
                String styleId = getStyleId(styleName);
                _par.setStyle(styleId);
            }

            ParagraphAlignment parAlign = XwUtils.convert(textPar.getAlignment());
            _par.setAlignment(parAlign);

            return x_par;
        }, textPar);
    }

    @Override
    public void textRun(TextRun textRun) {
        XwRun x_run = x_ptr.closest(xp.RUN);
        if (x_run == null) {
            IXwHaveRuns x_runs = x_ptr.closest(xp.HAVE_RUNS);
            if (x_runs == null) {
                IXwHavePars x_pars = x_ptr.closest(xp.HAVE_PARS);
                x_runs = x_pars.addPar();
            }
            x_run = x_runs.addRun();
        }
        XwRun x = x_run;
        scope(() -> x, textRun);
    }

    @Override
    public void table(Table table) {
        scope(() -> {
            int columnCount = table.getMaxColumnCount();
            int rowCount = table.getRowCount();
            XWPFTable _table = _document.createTable(rowCount, columnCount);
            XwTable x = new XwTable(_table);
            return x;
        }, table);
    }

    @Override
    public void tableRow(TableRow row, int index) {
        Table table = row.getParent();
        XwTable x_table = x_ptr.closest(xp.TABLE);

        scope(() -> {
            // XwTableRow x_row = x_table.addRow();
            XWPFTable _table = x_table.getElement();
            XWPFTableRow _row = _table.getRow(index);
            return new XwTableRow(_row);
        }, row);

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
        XwTableRow x_row = x_ptr.closest(xp.TABLE_ROW);
        if (x_row == null)
            throw new IllegalStateException("without x_row");

        XWPFTableRow _row = x_row.getElement();
        XWPFTableCell _cell = _row.getCell(index);
        XwTableCell x_cell = new XwTableCell(_cell);

        x_cell.addPlainText(cell.getText());
        // scope(() -> x_cell, cell);
    }

    @Override
    public void list(ListPar list) {
        scope(() -> {
            if (list.isMultiLevel()) {
                if (list.getLevel() == 0) {
                    list._docNum = nums.addNum(DECIMALS);
                } else {
                    list._docNum = list.getRootLevel()._docNum;
                }
            } else {
                list._docNum = nums.addNum(DECIMALS);
            }
            return x_ptr;
        }, list);
    }

    @Override
    public void listItem(IPar par, int index, int itemIndex) {
        if (par.isListItem()) {
            ListItem item = (ListItem) par;
            ListPar listPar = item.getParent();
            int level = item.getListLevel();
            DocNum _docNum = listPar._docNum;
            System.out.printf("List %s[%d] - docnum %s\n", //
                    ObjectInfo.getSimpleId(listPar), itemIndex, _docNum);

            boolean bakC = convertParToBreak;
            int bakI = convertParIndex;
            convertParToBreak = true;
            convertParIndex = 0;

            scope(() -> {
                IXwHavePars pars = x_ptr.closest(xp.HAVE_PARS);
                XwPar x_par = pars.addPar();
                XWPFParagraph _par = x_par.getElement();
                _docNum.apply(_par, level);
                return x_par;
            }, item);

            convertParToBreak = bakC;
            convertParIndex = bakI;
        } else {
            par.accept(this);
        }
    }

    @Override
    public void fontEnv(FontEnv fontEnv) {
        fontEnv.internalAccept(this);
    }

    @Override
    public void fontStyleEnv(FontStyleEnv fontStyleEnv) {
        IXwHaveRuns x_runs;
        if (x_ptr.haveRuns())
            x_runs = (IXwHaveRuns) x_ptr;
        else if (x_ptr.havePars()) {
            IXwHavePars x_pars = (IXwHavePars) x_ptr;
            XwPar x_par = x_pars.addPar();
            x_runs = x_par;
        } else if (x_ptr.isRun()) {
            XwRun x_run = (XwRun) x_ptr;
            XWPFParagraph _par = (XWPFParagraph) x_run.getElement().getParent();
            x_runs = new XwPar(_par);
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

        IXwNode bak = x_ptr;
        for (IRun run : fontStyleEnv.runs) {
            XwRun x_run = x_runs.addRun();
            XWPFRun _run = x_run.getElement();
            _run.setBold(bold);
            _run.setItalic(italic);
            _run.setStrikeThrough(strikeline);
            if (underline)
                _run.setUnderline(UnderlinePatterns.SINGLE);

            x_ptr = x_run;
            run.accept(this);
        }
        x_ptr = bak;
    }

    @Override
    public void chars(String s) {
        if (s == null || s.isEmpty())
            return;

        if (x_ptr.isRun()) {
            XwRun x_run = (XwRun) x_ptr;
            XWPFRun _run = x_run.getElement();
            XwUtils.addPlainText(_run, s);
        } else if (x_ptr.isPar()) {
            XwPar x_par = (XwPar) x_ptr;
            XWPFParagraph _par = x_par.getElement();
            XwUtils.addPlainText(_par, s);
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void hr(Hr hr) {
        XWPFParagraph hrPar = _document.createParagraph();
        hrPar.setBorderBottom(Borders.SINGLE);
    }

    @Override
    public void image(Image image) {
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
        IXwHaveRuns x_runs = x_ptr.closest(xp.HAVE_RUNS);
        if (x_runs == null) {
            IXwHavePars pars = x_ptr.closest(xp.HAVE_PARS);
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
