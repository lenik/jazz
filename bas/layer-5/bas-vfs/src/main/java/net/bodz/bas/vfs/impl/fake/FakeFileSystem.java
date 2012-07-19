package net.bodz.bas.vfs.impl.fake;

import net.bodz.bas.vfs.AbstractFileSystem;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathFormat;

public class FakeFileSystem
        extends AbstractFileSystem {

    @Override
    public IFile[] getRootFiles() {
        return new IFile[0];
    }

    @Override
    public IPath parse(String localPath)
            throws BadPathException {
        return null;
    }

    @Override
    public IFile resolve(String localPath)
            throws BadPathException {
        return null;
    }

    @Override
    public String format(IPath path, PathFormat pathFormat) {
        return path.getLocalPath();
    }

    private static final FakeFileSystem instance = new FakeFileSystem();

    public static FakeFileSystem getInstance() {
        return instance;
    }

}
