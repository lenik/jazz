package net.bodz.xml.models.pdb;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.xml.XMLs;

import org.junit.Test;

public class PDBTest {

    public static PDB bookdb;
    static {
        bookdb = new PDB();
        bookdb.setName("bookdb");
        bookdb.setLabel("书目数据库");
        bookdb.setCat("universe.books");
        bookdb.setTags("文学 图书馆");

        bookdb.addImport(null, "libraries.xml");
        bookdb.addImport("info", "libraries-info.xml");

        bookdb.setExtern(true);

        bookdb.addTable(TableTest.categoryTable);
        bookdb.addTable(TableTest.categoryTable);
        bookdb.addView(ViewTest.productSumView);
        bookdb.addTable(TableTest.categoryTable);
        bookdb.addTable(TableTest.categoryTable);
        bookdb.addView(ViewTest.productSumView);
    }

    @Test
    public void test1() throws Exception {
        String xml = XMLs.marshal(bookdb);
        System.out.println(xml);

        PDB restored = XMLs.unmarshal(xml, PDB.class);
        System.out.println("restored: " + restored);
        String xmlRestored = XMLs.marshal(restored);
        System.out.println(xmlRestored);

        assertEquals(xml, xmlRestored);
    }

}
