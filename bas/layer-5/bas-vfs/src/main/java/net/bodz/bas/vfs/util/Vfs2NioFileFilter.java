package net.bodz.bas.vfs.util;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

import net.bodz.bas.vfs.IFileFilter;
import net.bodz.bas.vfs.impl.nio.NioFile;

public class Vfs2NioFileFilter
        implements DirectoryStream.Filter<Path> {

    private final IFileFilter vfsFilter;

    public Vfs2NioFileFilter(IFileFilter vfsFilter) {
        if (vfsFilter == null)
            throw new NullPointerException("vfsFilter");
        this.vfsFilter = vfsFilter;
    }

    @Override
    public boolean accept(Path entry)
            throws IOException {
        NioFile nioFile = new NioFile(entry);
        return vfsFilter.accept(nioFile);
    }

}
