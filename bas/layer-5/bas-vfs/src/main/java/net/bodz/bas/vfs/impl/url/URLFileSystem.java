package net.bodz.bas.vfs.impl.url;

import net.bodz.bas.vfs.AbstractFileSystem;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathFormat;

public class URLFileSystem
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
        return null;
    }

    private static URLFileSystem instance = new URLFileSystem();

    public static URLFileSystem getInstance() {
        return instance;
    }

    public static void setInstance(URLFileSystem instance) {
        URLFileSystem.instance = instance;
    }

}
