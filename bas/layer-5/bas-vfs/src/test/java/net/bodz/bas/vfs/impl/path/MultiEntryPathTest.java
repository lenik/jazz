package net.bodz.bas.vfs.impl.path;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.vfs.path.MultiEntryPath;

public class MultiEntryPathTest
        extends Assert {

    @Test
    public void createByLocalPath() {
        MultiEntryPath path = new MultiEntryPath("abc", "foo/");
        assertEquals("abc:foo/", path.toString());
        assertTrue(path.isEntered());

        path = new MultiEntryPath("abc", "foo/bar");
        assertEquals("abc:foo/bar", path.toString());
        assertFalse(path.isEntered());
    }

    @Test
    public void createByLocalEntries() {
        MultiEntryPath path = new MultiEntryPath("abc", new String[] { "foo" }, true);
        assertEquals("abc:foo/", path.toString());
        assertTrue(path.isEntered());

        path = new MultiEntryPath("abc", new String[] { "foo", "bar" }, false);
        assertEquals("abc:foo/bar", path.toString());
        assertFalse(path.isEntered());
    }

}
