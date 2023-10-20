package net.bodz.bas.doc.node.conv;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.function.BiPredicate;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Assert;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.doc.io.DomWriter;
import net.bodz.bas.doc.node.Document;
import net.bodz.bas.doc.node.ListPar;
import net.bodz.bas.doc.node.Table;
import net.bodz.bas.doc.node.TableCell;
import net.bodz.bas.doc.node.TextPar;
import net.bodz.bas.doc.node.util.Css3ListStyle;
import net.bodz.bas.doc.property.HorizAlignment;
import net.bodz.bas.doc.property.MeasureLength;
import net.bodz.bas.doc.word.WordTemplates;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.ui.css3.property.ListStyleTypeMode;

public class WordConverterTest
        extends Assert {

    DomWriter out;
    boolean dumpDoc = true;

    String template = WordTemplates.NORMAL;
    String savePath = "/xxx/testconv.docx";

    String blah = Strings.repeat(3, " blah");

    String imagePath;
    String tableTop;
    String tableLeft;
    int tableRows = 4;
    int tableCols = 5;
    boolean mergeSomeCells = true;

    boolean addLists = true;
    {
        imagePath = "/usr/share/backgrounds/mate/nature/Aqua.jpg";
        // imagePath = "/xxx/logo.png";
        tableTop = "Grid Table 4 Accent 1";
        tableLeft = "Grid Table 5 Dark Accent 1";
    }

    void play()
            throws Exception {
        Document doc = buildDoc();
        if (dumpDoc)
            System.out.print(doc);

        XWPFDocument _doc = WordTemplates.createFromTemplate(template);
        WordConverter converter = new WordConverter(_doc);
        doc.accept(converter);

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        _doc.write(buf);

        FileResource target = new FileResource(savePath);
        target.write().write(buf.toByteArray());
    }

    Document buildDoc()
            throws Exception {
        Document doc = new Document();
        out = new DomWriter(doc);
        out.p();
        out.text("chapter title: hello");
        TextPar titlePar = (TextPar) out.stack.top();
        titlePar.setStyleClass("Title");
        out.end();

        out.chapter("chapter title: hello");
        out.section("section title: part 1");
        out.p("text: the first part of the hello chapter. ");
        out.p("text: the second part of the hello chapter. ");

        if (imagePath != null) {
            File image = new File(imagePath);
            out.print("Left");
            out.center();
            out.image(new FileResource(image), //
                    MeasureLength.millimeters(54.8), //
                    MeasureLength.millimeters(62.4));
            out.println("Right");

            out.p("image by URL");
            out.image(new URLResource("file://" + imagePath), //
                    MeasureLength.millimeters(54.8), //
                    MeasureLength.millimeters(62.4));
        }

        out.section("section title: part 2");

        out.print("text: the ");
        out.i("<i>first</i> ");
        out.print("part of ");

        out.b();
        out.print("<b>the ");
        out.i("<i>hello</i> ");
        out.print("chapter.</b> ");
        out.end(); // b

        out.println();

        out.println("text: end of the part 2.");
        out.hr();

        if (tableTop != null) {
            Table table = buildTable(tableTop, tableRows, tableCols, 1, 0, (Integer row, Integer col) -> row == 0);
            if (mergeSomeCells)
                mergeSomeCells(table);
        }

        if (tableLeft != null) {
            Table table = buildTable(tableLeft, tableRows, tableCols, 0, 1, (Integer row, Integer col) -> col == 0);
            if (mergeSomeCells)
                mergeSomeCells(table);
        }

        if (addLists) {
            buildLists();
        }

        return doc;
    }

    Table buildTable(String style, int rows, int cols, int firstRows, int firstColumns,
            BiPredicate<Integer, Integer> headerf) {
        String tableSpec = String.format("table %d rows, %d cols (header %d rows, %d cols)", //
                rows, cols, firstRows, firstColumns);
        out.chapter("chapter: " + tableSpec);
        out.println("following example shows a " + tableSpec);
        Table table = out.newTable(firstRows, firstColumns);
        table.setStyleClass(style);
        for (int row = 0; row < rows; row++) {
            out.tr();
            for (int col = 0; col < cols; col++) {
                String content = String.format("Cell %d,%d", row, col);
                boolean header = headerf.test(row, col);
                if (header)
                    out.th(content);
                else
                    out.td(content);
            }
        }
        out.endTable();
        return table;
    }

    void mergeSomeCells(Table table) {
        TableCell b0 = table.getCell(0, 1);
        b0.setColumnSpan(2);

        TableCell a1 = table.getCell(1, 0);
        a1.setRowSpan(3);

        TableCell c1 = table.getCell(1, 1);
        c1.setRowSpan(2);
        c1.setColumnSpan(3);

        TableCell e0 = table.getCell(0, 4);
        e0.setRowSpan(3);
    }

    void buildLists() {
        out.chapter("chapter: list");

        out.p("ordered list");
        out.ol();
        out.item("ol item 1" + blah);
        out.item("ol item 2");
        out.item("ol item 3");
        out.end();

        out.p("unordered list");
        out.ul();
        out.item("ul item 1");
        out.item("ul item 2" + blah);
        out.item("ul item 3");
        out.end();

        out.p("multi level");
        setMultiLevel(out.newOl());

        out.item("item 1");
        out.item();
        {
            out.text("item 2" + blah);
            setMultiLevel(out.newOl());
            out.item("item 2.1");
            out.item("item 2.2" + blah);
            out.item("item 2.3");
            // out.endList();
            out.endItem();
        }
        out.item();
        {
            out.text("item 3");
            setMultiLevel(out.newOl());
            out.item("item 3.1");
            out.item();
            {
                out.text("item 3.2");
                setMultiLevel(out.newOl());
                out.item("item 3.2.1" + blah);
                out.item("item 3.2.2");
                out.item("item 3.2.3");
                // out.endList();
                out.endItem();
            }
            out.item("item 3.3");
            // out.endList();
            out.endItem();
        }
        out.item("item 4");
        out.end();
    }

    void setMultiLevel(ListPar list) {
        list.setMultiLevel(true);

        int level = list.getLevel();

        ListStyleTypeMode mode;
        switch (level) {
        default:
        case 0:
            mode = ListStyleTypeMode.decimal;
            break;
        case 1:
            mode = ListStyleTypeMode.upper_roman;
            break;
        case 2:
            mode = ListStyleTypeMode.lower_latin;
            break;
        }
        Css3ListStyle style = new Css3ListStyle(mode);
        style.setJustify(HorizAlignment.LEFT);

        double left = 0; // 4 + 10 * level; // indent overall
        double align = 4; // left-align numbers at this position
        double hanging = left - align;

        style.setLeft(MeasureLength.millimeters(left));
        style.setHanging(MeasureLength.millimeters(hanging));

        // double tabStop = left + 14;
        // style.setTabPosition(MeasureLength.millimeters(tabStop));

        list.setListStyle(style);
    }

    public static void main(String[] args)
            throws Exception {
        WordConverterTest test = new WordConverterTest();
        test.play();
    }

}
