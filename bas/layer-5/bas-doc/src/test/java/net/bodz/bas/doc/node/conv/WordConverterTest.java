package net.bodz.bas.doc.node.conv;

import java.io.ByteArrayOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Assert;

import net.bodz.bas.doc.io.DomWriter;
import net.bodz.bas.doc.io.TableHeaderPosition;
import net.bodz.bas.doc.node.Document;
import net.bodz.bas.doc.node.ListPar;
import net.bodz.bas.doc.node.Table;
import net.bodz.bas.doc.node.TextPar;
import net.bodz.bas.doc.node.util.Css3ListStyle;
import net.bodz.bas.doc.property.HorizAlignment;
import net.bodz.bas.doc.property.MeasureLength;
import net.bodz.bas.doc.word.WordTemplates;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.ui.css3.property.ListStyleTypeMode;

public class WordConverterTest
        extends Assert {

    public static void main(String[] args)
            throws Exception {
        WordConverterTest test = new WordConverterTest();
        Document doc = test.buildDoc();
        XWPFDocument _doc = WordTemplates.getNormal();
        WordConverter conv = new WordConverter(_doc);
        doc.accept(conv);

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        _doc.write(buf);
        FileResource target = new FileResource("/xxx/testconv.docx");
        target.write().write(buf.toByteArray());
    }

    Document buildDoc()
            throws Exception {
        Document doc = new Document();
        DomWriter out = new DomWriter(doc);
        out.p();
        out.text("chapter title: hello");
        TextPar titlePar = (TextPar) out.stack.top();
        titlePar.setStyleClass("Title");
        out.end();

        out.chapter("chapter title: hello");
        out.section("section title: part 1");
        out.p("text: the first part of the hello chapter. ");
        out.p("text: the second part of the hello chapter. ");

        String path = "/usr/share/backgrounds/mate/nature/Aqua.jpg";
        path = "/xxx/logo.png";

//        File image = new File(path);
//        out.print("Left");
//        out.center();
//        out.image(new FileResource(image), //
//                MeasureLength.millimeters(54.8), //
//                MeasureLength.millimeters(62.4));
//        out.println("Right");
//
//        out.p("image by URL");
//        out.image(new URLResource("file://" + path), //
//                MeasureLength.millimeters(54.8), //
//                MeasureLength.millimeters(62.4));

        out.section("section title: part 2");
        out.print("text: the ");
        out.i("first(italic) ");
        out.print("part of ");

        out.b();
        out.print("the(bold) ");
        out.i("hello(b+i) ");
        out.print("chapter(bold). ");
        out.end(); // b

        out.println();

        out.println("text: end of the part 2.");
        out.hr();

        out.chapter("chapter: table");
        out.println("following example shows a table.");
        Table table = out.newTable();
        table.setHeaderPosition(TableHeaderPosition.LEFT);
        table.setStyleClass("Grid Table 4 Accent 1");
        out.tr();
        out.th("column 1");
        out.th("column 2");
        out.tr();
        out.td("cell 1,1");
        out.td("cell 1,2");
        out.td("cell 1,3");
        out.tr();
        out.td("cell 2,1");
        out.td("cell 2,2");
        out.td("cell 2,3");
        out.endTable();

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

        System.out.println(doc);

        return doc;
    }

    static final String blah = //
            " blah blah blah blah blah blah blah blah blah blah blah blah blah blah"//
                    + " blah blah blah blah blah blah blah blah blah blah blah blah blah blah"//
                    + " blah blah blah blah blah blah blah blah blah blah blah blah blah blah"//
                    + " blah blah blah blah blah blah blah blah blah blah blah blah blah blah"//
    ;

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

}
