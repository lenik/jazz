package net.bodz.xml.models.pdb;

import net.bodz.xml.util.XMLTest;

import org.junit.Test;

public class IndexTest {

    void test(String opts) {
        Index index = new Index();
        index.setOpts(opts);
        XMLTest.testMarshal(index);
    }

    @Test
    public void test1() throws Exception {
        test("F<fk1>(n:1 -> remote.field)");
        test("U« uk2 »(⇒ remote.field)");
    }

}
