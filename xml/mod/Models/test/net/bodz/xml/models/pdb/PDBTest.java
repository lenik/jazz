package net.bodz.xml.models.pdb;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXB;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.xml.XMLs;
import net.bodz.xml.models.Models;

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

    @Test
    public void testCollect() throws Exception {
        File dir = new File(Models.findModelHome("pdb"), "instances");
        File victalkXml = new File(dir, "victalk.pdb.xml");
        PDB victalk = JAXB.unmarshal(victalkXml, PDB.class);
        System.out.println("Victalk loaded: " + victalk);

        // String xml1 = XMLs.marshal(victalk);
        // PDB pdb1 = XMLs.unmarshal(xml1, PDB.class);
        // String xml1_nsFix = XMLs.marshal(pdb1);

        CollectVisitor visitor = new CollectVisitor();
        victalk.accept(visitor);
        Table prpTable = victalk.getTable("Prp");
        System.out.println("Table Prp: ");
        System.out.println(XMLs.marshal(prpTable));
        String rows1 = dumpRows(prpTable);

        String xml2 = XMLs.marshal(victalk);
        PDB pdb2 = XMLs.unmarshal(xml2, PDB.class);
        // String xml2_nsFix = XMLs.marshal(pdb2);
        pdb2.accept(visitor);
        String rows2 = dumpRows(pdb2.getTable("Prp"));

        // assertEquals(xml1_nsFix, xml2_nsFix);
        assertEquals(rows1, rows2);
    }

    String dumpRows(Table table) {
        CharOut out = new BCharOut();
        List<Instance> instances = table.getInstances();
        for (int rowIndex = 0; rowIndex < instances.size(); rowIndex++) {
            Instance inst = instances.get(rowIndex);
            RowData rowData = inst.getRowData();
            if (rowData == null)
                throw new NullPointerException("row[" + rowIndex + "]");
            out.printf("%d. %s\n", rowIndex, rowData);
        }
        return out.toString();
    }

}
