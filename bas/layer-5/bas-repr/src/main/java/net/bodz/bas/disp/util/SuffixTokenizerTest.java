package net.bodz.bas.disp.util;

import org.junit.Assert;
import org.junit.Test;

public class SuffixTokenizerTest
        extends Assert {

    @Test
    public void testTokens() {
        String name = "filename.foo*bar~cat-dog.spec+1.xml:ex";
        SuffixTokenizer toks = new SuffixTokenizer(name);
        assertEquals(".foo", toks.next());
        assertEquals("*bar", toks.next());
        assertEquals("~cat-dog", toks.next());
        assertEquals(".spec+1", toks.next());
        assertEquals(".xml", toks.next());
        assertEquals(":ex", toks.next());
        assertFalse(toks.hasNext());
    }

}
