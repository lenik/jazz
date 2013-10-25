package net.bodz.bas.vfs.impl.nio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

import net.bodz.bas.fn.IFilter;
import net.bodz.bas.vfs.IFile;

public class Vfs2NioFileFilter
        implements DirectoryStream.Filter<Path> {

    private final IFilter<IFile> vfsFilter;

    public Vfs2NioFileFilter(IFilter<IFile> vfsFilter) {
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
