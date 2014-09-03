package net.bodz.bas.site.vhost;

import org.junit.Assert;
import org.junit.Test;

public class DomainNameTokenizerTest
        extends Assert {

    @Test
    public void testA() {
        assertEquals("(a)", new DomainNameTokenizer("a", false).toString());
    }

    @Test
    public void testA_Reversed() {
        assertEquals("(a)", new DomainNameTokenizer("a", true).toString());
    }

    @Test
    public void testABC() {
        assertEquals("(a), (b), (c)", new DomainNameTokenizer("a.b.c", false).toString());
    }

    @Test
    public void testABC_Reversed() {
        assertEquals("(c), (b), (a)", new DomainNameTokenizer("a.b.c", true).toString());
    }

}
