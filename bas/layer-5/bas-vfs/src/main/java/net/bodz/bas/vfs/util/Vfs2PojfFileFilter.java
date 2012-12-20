package net.bodz.bas.vfs.util;

import java.io.File;
import java.io.FileFilter;

import net.bodz.bas.vfs.IFileFilter;
import net.bodz.bas.vfs.impl.pojf.PojfFile;

public class Vfs2PojfFileFilter
        implements FileFilter {

    private final IFileFilter vfsFilter;

    public Vfs2PojfFileFilter(IFileFilter vfsFilter) {
        if (vfsFilter == null)
            throw new NullPointerException("vfsFilter");
        this.vfsFilter = vfsFilter;
    }

    @Override
    public boolean accept(File pathname) {
        PojfFile file = new PojfFile(pathname);
        return vfsFilter.accept(file);
    }

}
