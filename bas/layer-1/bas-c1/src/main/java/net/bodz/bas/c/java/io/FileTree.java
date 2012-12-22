package net.bodz.bas.c.java.io;

import java.io.File;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.c.java.nio.DeleteOptions;

public class FileTree {

    public static boolean delete(File file, DeleteOption... options) {
        if (DeleteOptions.isDeleteTree(options)) {
            // TODO delete tree.
        }
        if (DeleteOptions.isRemoveEmptyParents(options)) {
            // TODO remove empty parents.
        }
        return file.delete();
    }

}
