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
        test("F(n:1 -> remote.field)");
        test("U(⇒ remote.field)");
    }

}
