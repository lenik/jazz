package net.bodz.bas.repr.path;

import org.junit.Assert;
import org.junit.Test;

public class BasicTokenQueueTest
        extends Assert {

    @Test
    public void testGetRemainingPath() {
        BasicTokenQueue queue = BasicTokenQueue.ofPath("a/b/c/d");
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
        BasicTokenQueue queue = BasicTokenQueue.ofPath("a/b/c");
        assertEquals("a", queue.shift());
        assertEquals("b", queue.shift());
        assertEquals("c", queue.shift());
        assertTrue(queue.isDone());
        assertNull(queue.shift());
    }

    @Test
    public void testParsePathEmptyMidlets() {
        BasicTokenQueue queue = BasicTokenQueue.ofPath("///////a");
        assertEquals("a", queue.shift());
    }

    @Test
    public void testParsePathTrailingSlash() {
        BasicTokenQueue queue = BasicTokenQueue.ofPath("a/");
        assertEquals("a", queue.shift());
        assertNull(queue.shift());
        assertTrue(queue.isEntered());
    }

    @Test
    public void testParsePathTrailingSlashes() {
        BasicTokenQueue queue = BasicTokenQueue.ofPath("a//////");
        assertEquals("a", queue.shift());
        assertNull(queue.shift());
        assertTrue(queue.isEntered());
    }

    @Test
    public void testParsePathEmpty() {
        BasicTokenQueue queue = BasicTokenQueue.ofPath("");
        assertNull(queue.shift());

        queue = BasicTokenQueue.ofPath("////");
        assertTrue(queue.isEntered());
        assertTrue(queue.isDone());
    }

}
