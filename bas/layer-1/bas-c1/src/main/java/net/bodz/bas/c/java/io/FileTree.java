package net.bodz.bas.c.java.io;

import java.io.File;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.c.java.nio.DeleteOptions;

public class FileTree {

    public static int delete(File file, DeleteOption... options) {
        int count = 0;

        if (DeleteOptions.isDeleteTree(options)) {
            // TODO delete tree.
        }

        if (file.delete())
            count++;

        if (DeleteOptions.isRemoveEmptyParents(options)) {
            // TODO remove empty parents.
        }

        return count;
    }

}
