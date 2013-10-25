package net.bodz.bas.vfs.impl.nio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFilenameFilter;

public class Vfs2NioFilenameFilter
        implements DirectoryStream.Filter<Path> {

    private final IFilenameFilter vfsFilter;
    private final IFile parentDir;

    public Vfs2NioFilenameFilter(IFilenameFilter vfsFilter, IFile parentDir) {
        if (vfsFilter == null)
            throw new NullPointerException("vfsFilter");
        this.vfsFilter = vfsFilter;
        this.parentDir = parentDir;
    }

    @Override
    public boolean accept(Path entry)
            throws IOException {
        Path fileName = entry.getFileName();

        String name;

        // NOTICE: Path "/" has a null filename.
        if (fileName == null)
            name = "";
        else
            name = fileName.toString();

        return vfsFilter.accept(parentDir, name);
    }

}
