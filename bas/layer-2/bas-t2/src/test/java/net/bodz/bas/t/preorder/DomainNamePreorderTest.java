package net.bodz.bas.t.preorder;

import org.junit.Assert;
import org.junit.Test;

public class DomainNamePreorderTest
        extends Assert {

    DomainNamePreorder preorder = DomainNamePreorder.getInstance();

    @Test
    public void testSamples()
            throws Exception {
        assertEquals(IPreorder.EQUALS, preorder.precompare("a", "a"));
        assertEquals(IPreorder.EQUALS, preorder.precompare("a.b", "a.b"));

        assertEquals(IPreorder.LESS_THAN, preorder.precompare("example.com", "a.example.com"));
        assertEquals(IPreorder.GREATER_THAN, preorder.precompare("a.example.com", "example.com"));
        assertEquals(IPreorder.GREATER_THAN, preorder.precompare("a.b.example.com", "example.com"));
    }

}
