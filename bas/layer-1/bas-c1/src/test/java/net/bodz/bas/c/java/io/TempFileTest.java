package net.bodz.bas.c.java.io;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.nio.file.FileFn;

public class TempFileTest
        extends Assert {

    static void touch(Path dir, String path)
            throws IOException {
        Path f = FilePath.joinHref(dir, path);
        FileFn.mkdirs(f.getParent());
        try (Writer out = Files.newBufferedWriter(f)) {
            out.write("stuff\n");
        }
    }

    @Test
    public void testDeleteTree_ContainSubdirs()
            throws IOException {
        Path tmpdir = TempFile.createTempDirectory();

        touch(tmpdir, "a/b/c");
        touch(tmpdir, "d/e/f");
        touch(tmpdir, "d/g/h");
        assertTrue("Prepare", Files.isDirectory(tmpdir.resolve("d/e")));
        assertTrue("Prepare", Files.isRegularFile(tmpdir.resolve("d/g/h")));

        TempFile.deleteTree(tmpdir);
        assertFalse(Files.exists(tmpdir));
    }

}
