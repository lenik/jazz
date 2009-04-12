package net.bodz.bas.io;

import static net.bodz.bas.test.TestDefs.entry;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.io.FileMask.EQU;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;

import org.junit.Test;

public class FileMaskTest {

    @Test
    public void testFormat() throws IOException {
        File tmpDir = Files.getTmpDir();
        EQU equ = FileMask.EQU(FileMask.BASIC);
        File tmpf = File.createTempFile("filemasktest", "tmp"); //$NON-NLS-1$ //$NON-NLS-2$
        try {
            Files.write(tmpf, "hello"); //$NON-NLS-1$

            TestDefs.tests(new TestEval<File>() {
                public Object eval(File input) throws Throwable {
                    return FileMask.getFileBits(input);
                }
            }, //
                    entry(equ, tmpDir, "drwxez"), // //$NON-NLS-1$
                    entry(equ, new File(tmpDir, "nonexist"), "fE"), // //$NON-NLS-1$ //$NON-NLS-2$
                    entry(equ, new File(tmpDir, "?nonexist"), "fE"), // //$NON-NLS-1$ //$NON-NLS-2$
                    entry(equ, tmpf, "frwxes") // //$NON-NLS-1$
                    );
        } finally {
            tmpf.delete();
        }
    }

}
