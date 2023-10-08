package net.bodz.bas.doc.node.conv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Assert;

import net.bodz.bas.doc.io.DomWriter;
import net.bodz.bas.doc.node.Document;
import net.bodz.bas.doc.word.WordTemplates;
import net.bodz.bas.io.res.builtin.FileResource;

public class StrictWordConverterTest
        extends Assert {

    public static void main(String[] args)
            throws IOException {
        StrictWordConverterTest test = new StrictWordConverterTest();
        Document doc = test.buildDoc();
        XWPFDocument _doc = WordTemplates.getNormal();
        StrictWordConverter conv = new StrictWordConverter(_doc);
        doc.accept(conv);

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        _doc.write(buf);
        FileResource target = new FileResource("/xxx/testconv.docx");
        target.write().write(buf.toByteArray());
    }

    Document buildDoc() {
        Document doc = new Document();
        DomWriter out = new DomWriter(doc);
        out.chapter("hello");
        out.section("part 1");
        out.text("the first part of the hello chapter. ");

        out.section("part 2");
        out.text("the ");
        out.i("first ");
        out.text("part of ");

        out.b();
        out.text("the ");
        out.i("hello ");
        out.text("chapter. ");
        out.end(); // b

        out.println();

        out.text("end of the part 2.");
        out.hr();

        out.chapter("table");
        out.text("following example shows a table.");
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

        return doc;
    }

}
