package net.bodz.bas.types.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringsTest {

    @Test
    public void testEllipseStringIntString() {
        String s = "abcdefghijklmnopqrstuvwxyz";
        assertEquals("..", Strings.ellipse(s, 2, "..."));
        assertEquals("a...", Strings.ellipse(s, 4, "..."));
    }

    @Test
    public void testEllipseStringIntStringStringString() {
        String s = "a/b/c/d/e/f/g/h/i";
        assertEquals("a...", Strings.ellipse(s, 4, "...", "/", "/"));
        assertEquals("a/b/...", Strings.ellipse(s, 7, "...", "/", "/"));
        assertEquals("a/b.../i", Strings.ellipse(s, 8, "...", "/", "/"));
        assertEquals("a/b/c.../i", Strings.ellipse(s, 10, "...", "/", "/"));
    }

}
