package net.bodz.bas.repr.path;

import org.junit.Assert;
import org.junit.Test;

public class TokenQueueTest
        extends Assert {

    @Test
    public void testGetRemainingPath() {
        TokenQueue queue = new TokenQueue("a/b/c/d");
        assertEquals("a/b/c/d", queue.getRemainingPath());

        queue.shift();
        assertEquals("b/c/d", queue.getRemainingPath());

        queue.shift();
        assertEquals("c/d", queue.getRemainingPath());

        queue.shift();
        assertEquals("d", queue.getRemainingPath());

        queue.shift();
        assertNull(queue.getRemainingPath());

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testParsePath() {
        TokenQueue queue = new TokenQueue("a/b/c");
        assertEquals("a", queue.shift());
        assertEquals("b", queue.shift());
        assertEquals("c", queue.shift());
        assertTrue(queue.isEmpty());
        assertNull(queue.shift());
    }

    @Test
    public void testParsePathEmptyMidlets() {
        TokenQueue queue = new TokenQueue("///////a");
        assertEquals("a", queue.shift());
    }

    @Test
    public void testParsePathTrailingSlash() {
        TokenQueue queue = new TokenQueue("a/");
        assertEquals("a", queue.shift());
        assertNull(queue.shift());
        assertTrue(queue.isEntered());
    }

    @Test
    public void testParsePathTrailingSlashes() {
        TokenQueue queue = new TokenQueue("a//////");
        assertEquals("a", queue.shift());
        assertNull(queue.shift());
        assertTrue(queue.isEntered());
    }

    @Test
    public void testParsePathEmpty() {
        TokenQueue queue = new TokenQueue("");
        assertNull(queue.shift());

        queue = new TokenQueue("////");
        assertTrue(queue.isEntered());
        assertTrue(queue.isEmpty());
    }

}
