package net.bodz.bas.vfs.util;

import java.io.File;
import java.io.FilenameFilter;

import net.bodz.bas.vfs.IFile;

public class FilenameFilterAdapterWithDir
        implements FilenameFilter {

    private final IFilenameFilter vfsFilter;
    private final IFile parentDir;

    public FilenameFilterAdapterWithDir(IFilenameFilter vfsFilter, IFile parentDir) {
        if (vfsFilter == null)
            throw new NullPointerException("vfsFilter");
        this.vfsFilter = vfsFilter;
        this.parentDir = parentDir;
    }

    @Override
    public boolean accept(File dir, String name) {
        return vfsFilter.accept(this.parentDir, name);
    }

}
