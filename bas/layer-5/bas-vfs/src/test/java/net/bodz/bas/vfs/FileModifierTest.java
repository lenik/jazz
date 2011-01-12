package net.bodz.bas.vfs;

import org.junit.Assert;
import org.junit.Test;

/**
 * @see FileModifier
 */
public class FileModifierTest
        extends Assert {

    @Test
    public void formatNone() {
        String actual = FileModifier.format(0);
        assertEquals("-", actual);
    }

    @Test
    public void formatSingleField() {
        String actual = FileModifier.format(FileModifier.EXECUTABLE);
        assertEquals("x", actual);
    }

    @Test
    public void formatMultipleFields() {
        String actual = FileModifier.format(//
                FileModifier.READABLE //
                        | FileModifier.EXECUTABLE //
                        | FileModifier.EMPTY //
                        | FileModifier.DIRECTORY);
        assertEquals("rxed", actual);
    }

    @Test
    public void parseNone() {
        int actual = FileModifier.parse("");
        assertEquals(0, actual);

        actual = FileModifier.parse("-");
        assertEquals(0, actual);
    }

    @Test
    public void parseSimple() {
        assertEquals(FileModifier.READABLE, //
                FileModifier.parse("r"));
    }

    @Test
    public void parseSequence() {
        assertEquals(FileModifier.READABLE //
                | FileModifier.WRITABLE //
                | FileModifier.EXECUTABLE, //
                FileModifier.parse("rwx"));
    }

    @Test
    public void parseReversed() {
        assertEquals(FileModifier.READABLE //
                | FileModifier.WRITABLE //
                | FileModifier.EXECUTABLE, //
                FileModifier.parse("xwr"));
    }

    @Test
    public void parseDuplicated() {
        assertEquals(FileModifier.READABLE //
                | FileModifier.WRITABLE //
                | FileModifier.EXECUTABLE, //
                FileModifier.parse("rwxwxwwxw--xw-xw-x"));
    }

}
