package net.bodz.bas.files;

import java.io.IOException;

import org.junit.Test;

public class FileTreeTest
        extends FileTestBase {

    @Test
    public void testDeleteTree()
            throws IOException {
        create("dt/a/b/c", "1");
        create("dt/d/e/f", "1");
        create("dt/d/g/h", "1");
        assertTrue("envprep", file("dt/d/e").isDirectory());
        assertTrue("envprep", file("dt/d/g/h").isFile());
        FileTree.deleteTree(file("dt"));
        assertFalse(file("dt").exists());
    }

}
