package net.bodz.bas.vfs.impl.pojf;

import java.io.File;
import java.io.FileFilter;

import net.bodz.bas.fn.IFilter;
import net.bodz.bas.vfs.IFile;

public class Vfs2PojfFileFilter
        implements FileFilter {

    private final IFilter<IFile> vfsFilter;

    public Vfs2PojfFileFilter(IFilter<IFile> vfsFilter) {
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
