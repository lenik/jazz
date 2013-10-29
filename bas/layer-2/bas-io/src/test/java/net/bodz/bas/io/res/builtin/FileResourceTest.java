package net.bodz.bas.io.res.builtin;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.io.FileTree;
import net.bodz.bas.c.java.nio.CreateOption;
import net.bodz.bas.c.java.nio.TreeDeleteOption;
import net.bodz.bas.io.res.tools.StreamWriting;

public class FileResourceTest
        extends Assert {

    @Test
    public void testCreateParents()
            throws IOException {
        File root = new File("/tmp/mkdirs");

        File abcd = new File(root, "a/b/c/d");
        FileResource res = new FileResource(abcd);

        StreamWriting writing = res.to(StreamWriting.class);
        writing.setOpenOptions(CreateOption.CREATE_PARENTS);
        writing.writeString("hello");

        assertTrue(abcd.exists());

        FileTree.delete(root, TreeDeleteOption.DELETE_TREE);
    }

}
