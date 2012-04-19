package net.bodz.bas.c.java.io;

import java.io.IOException;

import net.bodz.bas.test.mock.fs.AbstractTestTree;

public class MyTestTree
        extends AbstractTestTree {

    public void seed(String path)
            throws IOException {
        addFile(path, "This is " + path);
    }

    @Override
    protected void populate()
            throws Exception {
        seed("/");
    }

}
