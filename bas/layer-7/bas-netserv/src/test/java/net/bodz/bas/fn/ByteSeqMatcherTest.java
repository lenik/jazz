package net.bodz.bas.fn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ByteSeqMatcherTest
        extends Assert {

    ByteSeqMatcher matcher;

    @Before
    public void setUp() {
        matcher = new ByteSeqMatcher("abc");
    }

    @Test
    public void testPatternConstructor() {
        byte[] pattern = { 'a', 'b', 'c' };
        ByteSeqMatcher matcher = new ByteSeqMatcher(pattern);
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
    public void testSingleBytePattern() {
        ByteSeqMatcher singleByteMatcher = new ByteSeqMatcher("a");
        assertTrue(singleByteMatcher.test('a'));
        assertFalse(singleByteMatcher.test('b'));
    }

    @Test
    public void testMultipleBytePattern() {
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
    public void testByteArrayMethod() {
        byte[] input1 = { 'a', 'b', 'c' };
        assertTrue(matcher.test(input1));

        byte[] input2 = { 'd', 'e', 'f', 'a', 'b', 'c' };
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
        assertTrue(ByteSeqMatcher.contains("abc", "abc"));
        assertTrue(ByteSeqMatcher.contains("abc", "ab"));
        assertFalse(ByteSeqMatcher.contains("abc", "ac"));
    }

    @Test
    public void testStaticIndexOfMethod() {
        assertEquals(0, ByteSeqMatcher.indexOf("abc", "abc"));
        assertEquals(3, ByteSeqMatcher.indexOf("defabcghi", "abc"));
        assertEquals(-1, ByteSeqMatcher.indexOf("defabghi", "abc"));
    }

}