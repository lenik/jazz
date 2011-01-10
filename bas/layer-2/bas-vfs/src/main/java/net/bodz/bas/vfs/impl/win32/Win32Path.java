package net.bodz.bas.vfs.impl.win32;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IVolume;
import net.bodz.bas.vfs.path.AbstractPath;
import net.bodz.bas.vfs.path.IPath;

public class Win32Path
        extends AbstractPath {

    private static final long serialVersionUID = 1L;

    private final Win32Drive volume;

    public Win32Path(Win32Drive volume, String localPath) {
        super(localPath);
        if (volume == null)
            throw new NullPointerException("volume");
        this.volume = volume;
    }

    @Override
    public IVolume getVolume() {
        return volume;
    }

    @Override
    public IFile toFile() {
        return volume.resolveFile(localPath);
    }

    @Override
    public IPath getParentLayer() {
        toString();
        return null;
    }

}
