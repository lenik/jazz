package net.bodz.bas.vfs.impl.fake;

import net.bodz.bas.vfs.AbstractVolume;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.IllegalPathException;
import net.bodz.bas.vfs.path.PathFormat;

public class FakeVolume
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
        return path.getLocalPath();
    }

    private static final FakeVolume instance = new FakeVolume();

    public static FakeVolume getInstance() {
        return instance;
    }

}
