package net.bodz.bas.vfs.path;

import net.bodz.bas.vfs.IVfsDevice;

public abstract class DefaultPath
        extends AbstractPath {

    private static final long serialVersionUID = 1L;

    protected final IVfsDevice device;
    protected final String localPath;

    public DefaultPath(IVfsDevice device, String localPath) {
        if (localPath == null)
            throw new NullPointerException("localPath");
        this.device = device;
        this.localPath = localPath;
    }

    @Override
    public IVfsDevice getDevice() {
        return device;
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
