package net.bodz.bas.c.java.io;

import java.io.File;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.c.java.nio.DeleteOptions;

public class FileTree {

    public static int delete(File file, DeleteOption... options) {
        int count = 0;

        if (file.isDirectory()) {
            if (DeleteOptions.isDeleteTree(options))
                for (File child : file.listFiles())
                    count += delete(child, options);
        }

        if (file.delete())
            count++;

        if (DeleteOptions.isRemoveEmptyParents(options)) {
            while (true) {
                file = file.getParentFile();
                if (file == null)
                    break;
                if (!file.delete())
                    break;
            }
        }

        return count;
    }

}
