package net.bodz.redist.installer.util;

import java.io.File;

import net.bodz.bas.c.java.io.FilePath;

public class Utils {

    public static void removeEmptyParents(File start, File stopDir) {
        start = FilePath.canoniOf(start);
        stopDir = FilePath.canoniOf(stopDir);
        do {
            start.delete();
            start = start.getParentFile();
        } while (!start.equals(stopDir));
    }

}
