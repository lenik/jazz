package net.bodz.xml.models.pdb;

import java.io.File;
import java.io.FilenameFilter;
import java.io.StringWriter;

import javax.xml.bind.JAXB;

import net.bodz.xml.models.Models;

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
            // new ObjectFactory().
            PDB pdb = JAXB.unmarshal(testxml, PDB.class);
            System.out.println(pdb);
            StringWriter buf = new StringWriter();
            JAXB.marshal(pdb, buf);
            System.out.println(buf);
        }
    }

}
