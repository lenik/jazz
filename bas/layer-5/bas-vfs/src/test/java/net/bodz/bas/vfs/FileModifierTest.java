package net.bodz.bas.vfs;

import org.junit.Assert;
import org.junit.Test;

/**
 * @see FileFlags
 */
public class FileModifierTest
        extends Assert {

    @Test
    public void formatNone() {
        String actual = FileFlags.format(0);
        assertEquals("-", actual);
    }

    @Test
    public void formatSingleField() {
        String actual = FileFlags.format(FileFlags.EXECUTABLE);
        assertEquals("x", actual);
    }

    @Test
    public void formatMultipleFields() {
        String actual = FileFlags.format(//
                FileFlags.READABLE //
                        | FileFlags.EXECUTABLE //
                        | FileFlags.EMPTY //
                        | FileFlags.DIRECTORY);
        assertEquals("rxed", actual);
    }

    @Test
    public void parseNone() {
        int actual = FileFlags.parse("");
        assertEquals(0, actual);

        actual = FileFlags.parse("-");
        assertEquals(0, actual);
    }

    @Test
    public void parseSimple() {
        assertEquals(FileFlags.READABLE, //
                FileFlags.parse("r"));
    }

    @Test
    public void parseSequence() {
        assertEquals(FileFlags.READABLE //
                | FileFlags.WRITABLE //
                | FileFlags.EXECUTABLE, //
                FileFlags.parse("rwx"));
    }

    @Test
    public void parseReversed() {
        assertEquals(FileFlags.READABLE //
                | FileFlags.WRITABLE //
                | FileFlags.EXECUTABLE, //
                FileFlags.parse("xwr"));
    }

    @Test
    public void parseDuplicated() {
        assertEquals(FileFlags.READABLE //
                | FileFlags.WRITABLE //
                | FileFlags.EXECUTABLE, //
                FileFlags.parse("rwxwxwwxw--xw-xw-x"));
    }

}
