package net.bodz.redist.installer.util;

import java.io.File;

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
