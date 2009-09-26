package net.bodz.xml.models.pdb;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DocTest {

    void testWrap(String s, int width) throws Exception {
        String wrapped = Doc.wrap(s, width);
        System.out.printf("Wrapped: \"%s\"\n", wrapped);
        String t = Doc.unwrap(wrapped);
        assertEquals(s, t);
    }

    @Test
    public void testWrap() throws Exception {
        testWrap("ab", 2);
        testWrap("ab", 3);
        testWrap("abc", 2);
        testWrap("abc", 3);
        testWrap("abc", 4);
        testWrap("abcdefghijklmnopqrstuvwxyz", 3);
        testWrap("abcdefghijklmnopqrstuvwxyz", 10);
        testWrap("abcdefghijklmnopqrstuvwxyz", 20);
    }

}
