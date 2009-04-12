package net.bodz.dist.ins.util;

import java.io.File;

import net.bodz.bas.io.Files;

public class Utils {

    public static void removeEmptyParents(File start, File stopDir) {
        start = Files.canoniOf(start);
        stopDir = Files.canoniOf(stopDir);
        do {
            start.delete();
            start = start.getParentFile();
        } while (!start.equals(stopDir));
    }

}
