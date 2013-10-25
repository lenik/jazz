package net.bodz.pkg.sis;

import java.io.IOException;

import net.bodz.bas.io.res.IRandomResource;
import net.bodz.bas.vfs.IFile;

public class SisArchive
        implements ISisArchive {

    private IFile baseDir;

    public SisArchive(IFile baseDir) {
        if (baseDir == null)
            throw new NullPointerException("baseDir");
        this.baseDir = baseDir;
    }

    @Override
    public IFile getBaseDir() {
        return baseDir;
    }

    @Override
    public IRandomResource getResource(String name, boolean autoCreate)
            throws IOException {
        return null;
    }

    @Override
    public IRandomResource getDataResource(String name, boolean autoCreate)
            throws IOException {
        return null;
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }

}
