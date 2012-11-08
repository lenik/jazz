package net.bodz.bas.vfs.path;

import net.bodz.bas.vfs.IVfsDevice;
import net.bodz.bas.vfs.path.align.IPathAlignment;

public class DefaultPath
        extends AbstractPath {

    private static final long serialVersionUID = 1L;

    protected final IVfsDevice device;
    protected final String localPath;
    private final IPathAlignment alignment;

    public DefaultPath(IVfsDevice device, String localPath, IPathAlignment alignment) {
        if (localPath == null)
            throw new NullPointerException("localPath");
        this.device = device;
        this.alignment = alignment;
        this.localPath = localPath;
    }

    @Override
    public IVfsDevice getDevice() {
        return device;
    }

    @Override
    public final IPathAlignment getAlignment() {
        return alignment;
    }

    @Override
    public final String getLocalPath() {
        return localPath;
    }

    @Override
    public String[] getLocalEntries() {
        return localPath.split(SEPARATOR);
    }

}
