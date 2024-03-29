package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class TempFileTest
        extends Assert {

    static void touch(File dir, String path)
            throws IOException {
        File f = FilePath.joinHref(dir, path);
        f.getParentFile().mkdirs();
        FileWriter out = new FileWriter(f);
        out.write("stuff\n");
        out.close();
    }

    @Test
    public void testDeleteTree_ContainSubdirs()
            throws IOException {
        File tmpdir = TempFile.createTempDirectory();

        touch(tmpdir, "a/b/c");
        touch(tmpdir, "d/e/f");
        touch(tmpdir, "d/g/h");
        assertTrue("Prepare", new File(tmpdir, "d/e").isDirectory());
        assertTrue("Prepare", new File(tmpdir, "d/g/h").isFile());

        TempFile.deleteTree(tmpdir);
        assertFalse(tmpdir.exists());
    }

}
