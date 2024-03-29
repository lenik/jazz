package net.bodz.bas.t.preorder;

import java.io.File;

public class FileStartswithPreorder
        extends AbstractPreorder<File> {

    @Override
    public int compare2(File o1, File o2) {
        String path1 = o1.getPath();
        String path2 = o2.getPath();
        return path1.compareTo(path2);
    }

    @Override
    public File getPreceding(File file) {
        if (file == null)
            throw new NullPointerException("file");
        return file.getParentFile();
    }

    /**
     * @see PathStartswithPreorder
     */
    @Override
    public int precompare(File o1, File o2) {
        if (o1.equals(o2))
            return EQUALS;

        String path1 = o1.getPath();
        String path2 = o2.getPath();

        if (o1.isDirectory())
            path1 += "/";
        if (o2.isDirectory())
            path2 += "/";

        if (path1.startsWith(path2))
            return GREATER_THAN;
        if (path2.startsWith(path1))
            return LESS_THAN;

        return UNKNOWN;
    }

    public static final FileStartswithPreorder INSTANCE = new FileStartswithPreorder();


}
