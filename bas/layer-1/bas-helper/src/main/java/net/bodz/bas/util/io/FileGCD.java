package net.bodz.bas.util.io;

import java.io.File;

public class FileGCD {

    public static File getCommonParentFile(File... files) {
        if (files.length == 0)
            return null;
        File base = files[0];
        for (int i = 1; i < files.length; i++) {
            base = getCommonParentFile(base, files[i]);
            if (base == null)
                return null;
        }
        return base;
    }

    public static File getCommonParentFile(File a, File b) {
        if (a == null || b == null)
            return null;
        if (a.equals(b))
            return a;
        final File _a = a.getParentFile();
        for (File i = a; i != null; i = i.getParentFile())
            if (i.equals(b))
                return b;
        final File _b = b.getParentFile();
        for (File i = b; i != null; i = i.getParentFile())
            if (i.equals(a))
                return a;
        return getCommonParentFile(_a, _b);
    }

}
