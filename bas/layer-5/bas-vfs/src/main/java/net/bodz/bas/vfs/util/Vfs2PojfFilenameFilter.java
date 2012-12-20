package net.bodz.bas.vfs.util;

import java.io.File;
import java.io.FilenameFilter;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFilenameFilter;

public class Vfs2PojfFilenameFilter
        implements FilenameFilter {

    private final IFilenameFilter vfsFilter;
    private final IFile parentDir;

    public Vfs2PojfFilenameFilter(IFilenameFilter vfsFilter, IFile parentDir) {
        if (vfsFilter == null)
            throw new NullPointerException("vfsFilter");
        this.vfsFilter = vfsFilter;
        this.parentDir = parentDir;
    }

    @Override
    public boolean accept(File dir, String name) {
        // dir is ignored.
        return vfsFilter.accept(this.parentDir, name);
    }

}
