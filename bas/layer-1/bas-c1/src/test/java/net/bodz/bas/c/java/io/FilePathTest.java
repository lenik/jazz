package net.bodz.bas.c.java.io;

import org.junit.Assert;
import org.junit.Test;

public class FilePathTest
        extends Assert {

    @Test
    public void testGetRelativeName() {
        class D {
            void o(String expected, String ref, String path) {
                String actual = FilePath.getRelativePath(path, ref);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("..",/* input */"a/b", "a");
        d.o(".",/* input */"a/b", "a/");
        d.o("b",/* input */"a/b", "a/b");
    }

}
