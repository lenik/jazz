package net.bodz.bas.c.java.io;

import java.io.File;

public class FileDirs {

    public static void removeEmptyParents(File start, File stopDir) {
        start = FilePath.canoniOf(start);
        stopDir = FilePath.canoniOf(stopDir);
        do {
            start.delete();
            start = start.getParentFile();
        } while (!start.equals(stopDir));
    }

}
