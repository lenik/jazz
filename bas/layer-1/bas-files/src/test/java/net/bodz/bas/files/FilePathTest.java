package net.bodz.bas.files;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

public class FilePathTest {

    static Object[] _(Object... args) {
        return args;
    }

    @Test
    public void testGetRelativeName() {
        class D {
            void o(Object[] input, String expected) {
                File start = new File((String) input[0]);
                File file = new File((String) input[1]);
                String actual = FilePath.getRelativeName(file, start);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o(_("a", "a/b"), "b");
        d.o(_("a/", "a/b"), "b");
        d.o(_("a/b", "a/b"), "");
    }

}
