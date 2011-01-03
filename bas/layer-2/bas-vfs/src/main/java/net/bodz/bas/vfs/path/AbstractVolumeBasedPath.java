package net.bodz.bas.vfs.path;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IVolume;
import net.bodz.bas.vfs.path.align.IPathAlignment;

public abstract class AbstractVolumeBasedPath
        extends AbstractPath {
    private final IVolume volume;

    public AbstractVolumeBasedPath(IVolume volume, String localPath, IPathAlignment alignment) {
        super(localPath, alignment);
        if (volume == null)
            throw new NullPointerException("volume");
        this.volume = volume;
    }

    @Override
    public final IVolume getVolume() {
        return volume;
    }

    @Override
    public IFile toFile() {
        return getVolume().resolveFile(localPath);
    }

    @Override
    public IPath getParentLayer() {
        IFile deviceFile = volume.getDeviceFile();
        if (deviceFile == null)
            return null;
        return deviceFile.getPath();
    }

    @Override
    protected IPath resolveLocal(String localPath) {
        return volume.resolve(localPath);
    }

}
