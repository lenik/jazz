package net.bodz.bas.repr.path;

import org.junit.Assert;
import org.junit.Test;

public class BasicTokenQueueTest
        extends Assert {

    @Test
    public void testGetRemainingPath() {
        TokenArray queue = TokenArray.ofPath("a/b/c/d");
        assertEquals("a/b/c/d", queue.getRemainingPath());

        queue.shift();
        assertEquals("b/c/d", queue.getRemainingPath());

        queue.shift();
        assertEquals("c/d", queue.getRemainingPath());

        queue.shift();
        assertEquals("d", queue.getRemainingPath());

        queue.shift();
        assertNull(queue.getRemainingPath());

        assertTrue(queue.isDone());
    }

    @Test
    public void testParsePath() {
        TokenArray queue = TokenArray.ofPath("a/b/c");
        assertEquals("a", queue.shift());
        assertEquals("b", queue.shift());
        assertEquals("c", queue.shift());
        assertTrue(queue.isDone());
        assertNull(queue.shift());
    }

    @Test
    public void testParsePathEmptyMidlets() {
        TokenArray queue = TokenArray.ofPath("///////a");
        assertEquals("a", queue.shift());
    }

    @Test
    public void testParsePathTrailingSlash() {
        TokenArray queue = TokenArray.ofPath("a/");
        assertEquals("a", queue.shift());
        assertNull(queue.shift());
        assertTrue(queue.isEntered());
    }

    @Test
    public void testParsePathTrailingSlashes() {
        TokenArray queue = TokenArray.ofPath("a//////");
        assertEquals("a", queue.shift());
        assertNull(queue.shift());
        assertTrue(queue.isEntered());
    }

    @Test
    public void testParsePathEmpty() {
        TokenArray queue = TokenArray.ofPath("");
        assertNull(queue.shift());

        queue = TokenArray.ofPath("////");
        assertTrue(queue.isEntered());
        assertTrue(queue.isDone());
    }

}
