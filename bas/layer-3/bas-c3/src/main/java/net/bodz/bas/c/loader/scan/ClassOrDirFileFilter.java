package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.util.zip.ZipEntry;


public class ClassOrDirFileFilter
        implements IFileOrEntryFilter {

    @Override
    public boolean accept(File file) {
        if (file.isDirectory())
            return true;
        String name = file.getName();
        if (name.endsWith(".class"))
            return true;
        return false;
    }

    @Override
    public boolean accept(ZipEntry entry) {
        String entryName = entry.getName();
        return entryName.endsWith(".class");
    }

    public static final ClassOrDirFileFilter INSTANCE = new ClassOrDirFileFilter();

}
