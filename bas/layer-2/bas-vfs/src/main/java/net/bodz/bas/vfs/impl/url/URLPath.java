package net.bodz.bas.vfs.impl.url;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IVolume;
import net.bodz.bas.vfs.path.AbstractPath;
import net.bodz.bas.vfs.path.IPath;

public class URLPath
        extends AbstractPath {

    private static final long serialVersionUID = 1L;

    public URLPath(String localPath) {
        super(localPath);
    }

    @Override
    public IVolume getVolume() {
        return null;
    }

    @Override
    public IFile toFile() {
        return null;
    }

    @Override
    public IPath getParentLayer() {
        return null;
    }

}
