package net.bodz.bas.vfs;

import net.bodz.bas.mf.std.EmptyAttributes;
import net.bodz.bas.mf.std.IAttributes;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public abstract class AbstractVfsDevice
        implements IVfsDevice {

    private final IVfsDriver driver;
    private final String deviceSpec;
    private final String protocol;

    public AbstractVfsDevice(IVfsDriver driver, String protocol, String deviceSpec) {
        if (driver == null)
            throw new NullPointerException("driver");
        this.driver = driver;
        this.protocol = protocol;
        this.deviceSpec = deviceSpec;
    }

    @Override
    public IVfsDriver getDriver() {
        return driver;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public String getDeviceSpec() {
        return deviceSpec;
    }

    @Override
    public IFile getDeviceFile() {
        return null;
    }

    @Override
    public IAttributes getAttributes() {
        return EmptyAttributes.getInstance();
    }

    @Override
    public IPath getRootPath() {
        IFile rootFile = getRootFile();
        if (rootFile == null)
            return null;
        else
            return rootFile.getPath();
    }

    @Override
    public IFile resolve(String localPath)
            throws BadPathException, FileResolveException {
        IPath path = parse(localPath);
        return resolve(path);
    }

    @Override
    public void addFileListener(IFile watchFile, IFileListener listener)
            throws VFSException {
        throw new UnsupportedOperationException("file-listener");
    }

    @Override
    public void removeFileListener(IFile watchFile, IFileListener listener)
            throws VFSException {
        throw new UnsupportedOperationException("file-listener");
    }

}
