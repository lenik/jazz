package net.bodz.bas.vfs.impl.url;

import net.bodz.bas.vfs.AbstractVolume;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.IllegalPathException;
import net.bodz.bas.vfs.path.PathFormat;

public class URLVolume
        extends AbstractVolume {

    @Override
    public IPath getRootPath() {
        return null;
    }

    @Override
    public IPath resolve(String localPath)
            throws IllegalPathException {
        return null;
    }

    @Override
    public IFile resolveFile(String localPath)
            throws IllegalPathException {
        return null;
    }

    @Override
    public String format(IPath path, PathFormat pathFormat) {
        return null;
    }

    private static URLVolume instance = new URLVolume();

    public static URLVolume getInstance() {
        return instance;
    }

    public static void setInstance(URLVolume instance) {
        URLVolume.instance = instance;
    }

}
