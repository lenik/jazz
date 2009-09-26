package net.bodz.xml.models.pdb;

import java.io.File;
import java.io.FilenameFilter;

import javax.xml.bind.JAXB;

import net.bodz.xml.models.Models;
import net.bodz.xml.util.XMLTest;

import org.junit.Test;

public class FullTest {

    @Test
    public void testMarshal() throws Exception {
        File testdir = new File(Models.findModelHome("pdb"), "test");
        File[] testxmls = testdir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml");
            }
        });
        for (File testxml : testxmls) {
            System.out.println("Constructed from xml-file: " + testxml);
            PDB pdb = JAXB.unmarshal(testxml, PDB.class);
            System.out.println("  PDB: " + pdb);

            CollectVisitor visitor = new CollectVisitor();
            pdb.accept(visitor);

            XMLTest.testMarshal(pdb);
        }
    }

}
