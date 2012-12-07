package net.bodz.bas.repr.path;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.TokenQueue;

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
        assertEquals("", queue.getRemainingPath());

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
        assertEquals(ITokenQueue.INDEX, queue.shift());
        assertNull(queue.shift());
    }

    @Test
    public void testParsePathTrailingSlashes() {
        TokenQueue queue = new TokenQueue("a//////");
        assertEquals("a", queue.shift());
        assertEquals(ITokenQueue.INDEX, queue.shift());
        assertNull(queue.shift());
    }

    @Test
    public void testParsePathEmpty() {
        TokenQueue queue = new TokenQueue("");
        assertNull(queue.shift());

        queue = new TokenQueue("////");
        assertEquals(ITokenQueue.INDEX, queue.shift());
    }

}
