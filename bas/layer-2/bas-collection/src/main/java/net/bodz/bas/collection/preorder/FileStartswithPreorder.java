package net.bodz.bas.collection.preorder;

import java.io.File;
import java.nio.file.Path;

public class FileStartswithPreorder
        extends AbstractPreorder<File> {

    @Override
    public File getPreceding(File file) {
        if (file == null)
            throw new NullPointerException("file");
        return file.getParentFile();
    }

    @Override
    public int precompare(File o1, File o2) {
        if (o1.equals(o2))
            return EQUALS;
        Path p1 = o1.toPath();
        Path p2 = o2.toPath();
        return PathStartswithPreorder.getInstance().precompare(p1, p2);
    }

    static final FileStartswithPreorder instance = new FileStartswithPreorder();

    public static FileStartswithPreorder getInstance() {
        return instance;
    }

}
