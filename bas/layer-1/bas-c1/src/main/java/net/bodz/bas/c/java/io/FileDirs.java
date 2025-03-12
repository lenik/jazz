package net.bodz.bas.c.java.io;

import java.io.File;
import java.nio.file.Path;

import net.bodz.bas.c.java.nio.file.FileFn;

public class FileDirs {

    public static final long DIR_FILE_SIZE = 0;

    public static boolean removeEmptyParents(File start, File stopDir) {
        start = FilePath.canoniOf(start);
        stopDir = FilePath.canoniOf(stopDir);
        do {
            if (!start.delete())
                return false;
            start = start.getParentFile();
        } while (!start.equals(stopDir));
        return true;
    }

    public static void removeEmptyParents(Path start, Path stopDir) {
        start = FilePath.canoniOf(start);
        stopDir = FilePath.canoniOf(stopDir);
        do {
            FileFn.delete(start);
            start = start.getParent();
        } while (!start.equals(stopDir));
    }

}
