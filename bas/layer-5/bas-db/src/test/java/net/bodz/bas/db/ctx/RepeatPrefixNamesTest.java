package net.bodz.bas.db.ctx;

import org.junit.Assert;
import org.junit.Test;

public class RepeatPrefixNamesTest
        extends Assert {

    @Test
    public void testRepeat() {
        testRepeat("f", "f");
        testRepeat("foo", "foo");
        testRepeat("f.b", "f/f.b");
        testRepeat("foo.bar", "foo/foo.bar");
        testRepeat(".foo.bar", "/.foo/.foo.bar");
        testRepeat("foo.bar.", "foo/foo.bar/foo.bar.");
    }

    void testRepeat(String in, String out) {
        String actual = RepeatPrefixNames.DEFAULT.repeat(in);
        assertEquals(out, actual);

        String orig = RepeatPrefixNames.DEFAULT.compact(actual);
        assertEquals(orig, in);
    }

    @Test
    public void testExpand() {
        testExpand("f", "f");
        testExpand("foo", "foo");
        testExpand("f.b", "f/f.b");
        testExpand("foo.bar", "foo/foo.bar");
        testExpand(".foo.bar", "/.foo/.foo.bar");
        testExpand("foo.bar.", "foo/foo.bar/foo.bar.");

        testExpand("a/b.c", "a/b/b.c");
        testExpand("a/b.c/d.e", "a/b/b.c/d/d.e");
    }

    void testExpand(String in, String out) {
        String actual = RepeatPrefixNames.DEFAULT.expand(in);
        assertEquals(out, actual);

//        String orig = RepeatPrefixNames.DEFAULT.compact(actual);
//        assertEquals(orig, in);
    }

}
