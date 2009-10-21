package net.bodz.bas.io;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class FileMaskTest {

    @Test
    public void testFormat() throws IOException {
        class D {
            void o(File input, String expected) {
                int maskedBits = FileMask.BASIC.getMask() & FileMask.getFileBits(input);
                String actual = FileMask.format(maskedBits);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //

        File tmpDir = Files.getTmpDir();
        File tmpf = File.createTempFile("filemasktest", "tmp"); //$NON-NLS-1$ //$NON-NLS-2$
        try {
            Files.write(tmpf, "hello"); //$NON-NLS-1$

            d.o(tmpDir, "drwxez"); //$NON-NLS-1$
            d.o(new File(tmpDir, "nonexist"), "fE"); //$NON-NLS-1$ //$NON-NLS-2$
            d.o(new File(tmpDir, "?nonexist"), "fE"); //$NON-NLS-1$ //$NON-NLS-2$
            d.o(tmpf, "frwxes"); //$NON-NLS-1$
        } finally {
            tmpf.delete();
        }
    }

}
