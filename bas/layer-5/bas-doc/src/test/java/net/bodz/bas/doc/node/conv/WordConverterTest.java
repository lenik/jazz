package net.bodz.bas.doc.node.conv;

import java.io.ByteArrayOutputStream;
import java.io.File;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Assert;

import net.bodz.bas.doc.io.DomWriter;
import net.bodz.bas.doc.node.Document;
import net.bodz.bas.doc.node.TextPar;
import net.bodz.bas.doc.property.MeasureLength;
import net.bodz.bas.doc.word.WordTemplates;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.builtin.URLResource;

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

        File image = new File(path);
        out.print("Left");
        out.center();
        out.image(new FileResource(image), //
                MeasureLength.mm(54.8), //
                MeasureLength.mm(62.4));
        out.println("Right");

        out.p("image by URL");
        out.image(new URLResource("file://" + path), //
                MeasureLength.mm(54.8), //
                MeasureLength.mm(62.4));

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
        out.table();
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
        out.item("item 1");
        out.item("item 2");
        out.item("item 3");
        out.end();

        out.p("unordered list");
        out.ul();
        out.item("item 1");
        out.item("item 2");
        out.item("item 3");
        out.end();

        out.p("multi level");
        out.ol();
        out.item("item 1");
        out.item();
        {
            out.text("item 2");
            out.ol();
            out.item("item 2.1");
            out.item("item 2.2");
            out.item("item 2.3");
            // out.endList();
            out.endItem();
        }
        out.item();
        {
            out.text("item 3");
            out.ol();
            out.item("item 3.1");
            out.item();
            {
                out.text("item 3.2");
                out.ol();
                out.item("item 3.2.1");
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

        return doc;
    }

}
