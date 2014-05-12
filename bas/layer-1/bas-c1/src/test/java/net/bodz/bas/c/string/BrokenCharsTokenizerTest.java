package net.bodz.bas.c.string;

import org.junit.Assert;
import org.junit.Test;

public class BrokenCharsTokenizerTest
        extends Assert {

    static char[] ARROW = "->".toCharArray();

    @Test
    public void testArrowStrict() {
        String s = "foo->bar";
        BrokenCharsTokenizer tokens = new BrokenCharsTokenizer(s, ARROW);
        String w1 = tokens.next();
        assertEquals("foo", w1);
        assertEquals(3, tokens.getEnd());
        assertEquals(5, tokens.getNextStart());
        String w2 = tokens.next();
        assertEquals("bar", w2);
        assertEquals(8, tokens.getEnd());
        assertEquals(8, tokens.getNextStart());
        assertFalse(tokens.hasNext());
    }

    @Test
    public void testArrowLoose0() {
        String s = "foo ->bar";
        BrokenCharsTokenizer tokens = new BrokenCharsTokenizer(s, ARROW);
        String w1 = tokens.next();
        assertEquals("foo ", w1);
        assertEquals(4, tokens.getEnd());
        assertEquals(6, tokens.getNextStart());
        String w2 = tokens.next();
        assertEquals("bar", w2);
        assertEquals(9, tokens.getEnd());
        assertEquals(9, tokens.getNextStart());
        assertFalse(tokens.hasNext());
    }

    @Test
    public void testArrowLoose1() {
        String s = "foo- >bar";
        BrokenCharsTokenizer tokens = new BrokenCharsTokenizer(s, ARROW);
        String w1 = tokens.next();
        assertEquals("foo", w1);
        assertEquals(3, tokens.getEnd());
        assertEquals(6, tokens.getNextStart());
        String w2 = tokens.next();
        assertEquals("bar", w2);
        assertEquals(9, tokens.getEnd());
        assertEquals(9, tokens.getNextStart());
        assertFalse(tokens.hasNext());
    }

    @Test
    public void testArrowLoose2() {
        String s = "foo-> bar";
        BrokenCharsTokenizer tokens = new BrokenCharsTokenizer(s, ARROW);
        String w1 = tokens.next();
        assertEquals("foo", w1);
        assertEquals(3, tokens.getEnd());
        assertEquals(5, tokens.getNextStart());
        String w2 = tokens.next();
        assertEquals(" bar", w2);
        assertEquals(9, tokens.getEnd());
        assertEquals(9, tokens.getNextStart());
        assertFalse(tokens.hasNext());
    }

}
