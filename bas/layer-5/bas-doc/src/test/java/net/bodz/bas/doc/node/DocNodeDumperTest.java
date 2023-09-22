package net.bodz.bas.doc.node;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.doc.attr.ElementType;
import net.bodz.bas.doc.node.DocDocument;
import net.bodz.bas.doc.node.DocWriter;
import net.bodz.bas.io.Stdio;

public class DocNodeDumperTest
        extends Assert {

    @Test
    public void test() {
    }

    public static void main(String[] args) {
        DocDocument doc = new DocDocument();
        doc.getTitle().setText("document 1");

        DocWriter out = new DocWriter(doc);
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
    }

}
