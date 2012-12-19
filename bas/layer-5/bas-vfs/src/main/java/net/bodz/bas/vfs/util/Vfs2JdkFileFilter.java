package net.bodz.bas.vfs.util;

import java.io.File;
import java.io.FileFilter;

import net.bodz.bas.vfs.IFileFilter;
import net.bodz.bas.vfs.impl.jdk.JdkFile;

public class Vfs2JdkFileFilter
        implements FileFilter {

    private final IFileFilter vfsFilter;

    public Vfs2JdkFileFilter(IFileFilter vfsFilter) {
        if (vfsFilter == null)
            throw new NullPointerException("vfsFilter");
        this.vfsFilter = vfsFilter;
    }

    @Override
    public boolean accept(File pathname) {
        JdkFile file = new JdkFile(pathname);
        return vfsFilter.accept(file);
    }

}
