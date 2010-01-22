package net.bodz.bas.fs;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.files.FileTemp;
import net.bodz.bas.io.resource.builtin.LocalFileResource;

import org.junit.Test;

public class FileMaskTest {

    @Test
    public void testFormat()
            throws IOException {
        class D {
            void o(File input, String expected) {
                int maskedBits = FileMask.BASIC.getMask() & FileModifiers.getFileBits(input);
                String actual = FileModifiers.format(maskedBits);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //

        File tmpDir = FileTemp.getTmpDir();
        File tmpf = File.createTempFile("filemasktest", "tmp");
        try {
            new LocalFileResource(tmpf).forWrite().write("hello");

            d.o(tmpDir, "drwxez");
            d.o(new File(tmpDir, "nonexist"), "fE");
            d.o(new File(tmpDir, "?nonexist"), "fE");
            d.o(tmpf, "frwxes");
        } finally {
            tmpf.delete();
        }
    }

}
