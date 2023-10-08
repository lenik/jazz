package net.bodz.bas.doc.node;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.doc.io.DomWriter;
import net.bodz.bas.doc.property.ElementType;
import net.bodz.bas.io.Stdio;

public class DocNodeDumperTest
        extends Assert {

    @Test
    public void test() {
    }

    public static void main(String[] args) {
        Document doc = new Document();
        doc.title.setText("document 1");

        DomWriter out = new DomWriter(doc);
        out.section("section 1");
        out.text("section text");

        out.table();
        out.tr();
        out.td().text("field 1").end();
        out.td().text("field 2").end();
        out.end(ElementType.TR);
        out.end(); // table

        out.end(); // section

        doc.dump(Stdio.cout);

        String md = doc.toMarkdown();
        System.out.println(md);
    }

}
