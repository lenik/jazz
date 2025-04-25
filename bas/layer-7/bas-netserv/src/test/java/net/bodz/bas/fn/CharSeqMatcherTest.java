package net.bodz.bas.fn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CharSeqMatcherTest
        extends Assert {

    CharSeqMatcher matcher;

    @Before
    public void setUp() {
        matcher = new CharSeqMatcher("abc");
    }

    @Test
    public void testPatternConstructor() {
        char[] pattern = { 'a', 'b', 'c' };
        CharSeqMatcher matcher = new CharSeqMatcher(pattern);
        assertArrayEquals(pattern, matcher.pattern);
    }

    @Test
    public void testResetMethod() {
        matcher.test('a');
        matcher.reset();
        assertEquals(0, matcher.end);
        assertEquals(0, matcher.len);
    }

    @Test
    public void testSingleCharPattern() {
        CharSeqMatcher singleCharMatcher = new CharSeqMatcher("a");
        assertTrue(singleCharMatcher.test('a'));
        assertFalse(singleCharMatcher.test('b'));
    }

    @Test
    public void testMultipleCharPattern() {
        assertTrue(matcher.test("abc"));
        assertFalse(matcher.test("ab"));
        assertFalse(matcher.test("ac"));
        assertTrue(matcher.test("defabcghi"));
        assertFalse(matcher.test("defabghi"));
    }

    @Test
    public void testStringMethod() {
        assertTrue(matcher.test("abc"));
        assertFalse(matcher.test("ab"));
        assertFalse(matcher.test("ac"));
        assertTrue(matcher.test("defabcghi"));
        assertFalse(matcher.test("defabghi"));
    }

    @Test
    public void testCharArrayMethod() {
        char[] input1 = { 'a', 'b', 'c' };
        assertTrue(matcher.test(input1));

        char[] input2 = { 'd', 'e', 'f', 'a', 'b', 'c' };
        assertTrue(matcher.test(input2, 3, 6));
    }

    @Test
    public void testSearchMethod() {
        assertEquals(0, matcher.search("abc"));
        assertEquals(3, matcher.search("defabcghi"));
        assertEquals(-1, matcher.search("defabghi"));
    }

    @Test
    public void testStaticContainsMethod() {
        assertTrue(CharSeqMatcher.contains("abc", "abc"));
        assertTrue(CharSeqMatcher.contains("abc", "ab"));
        assertFalse(CharSeqMatcher.contains("abc", "ac"));
    }

    @Test
    public void testStaticIndexOfMethod() {
        assertEquals(0, CharSeqMatcher.indexOf("abc", "abc"));
        assertEquals(3, CharSeqMatcher.indexOf("defabcghi", "abc"));
        assertEquals(-1, CharSeqMatcher.indexOf("defabghi", "abc"));
    }

}