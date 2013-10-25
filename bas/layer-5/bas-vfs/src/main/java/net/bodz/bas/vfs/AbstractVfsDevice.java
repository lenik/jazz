package net.bodz.bas.vfs;

import net.bodz.bas.typer.std.EmptyAttributes;
import net.bodz.bas.typer.std.IAttributes;
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
    public final IFile resolve(String localPath)
            throws FileResolveException {
        return resolve(localPath, FileResolveOptions.DEFAULT);
    }

    @Override
    public final IFile resolve(IPath _path)
            throws FileResolveException {
        return resolve(_path, FileResolveOptions.DEFAULT);
    }

    @Override
    public final IFile resolve(String localPath, FileResolveOptions options)
            throws FileResolveException {
        IFile file = _resolveNoRec(localPath);
        if (options.isFollowLinks() && file.getAttributes().isSymbolicLink()) {
            // XXX follow symlink
        }
        return file;
    }

    @Override
    public final IFile resolve(IPath _path, FileResolveOptions options)
            throws FileResolveException {
        IFile file = _resolveNoRec(_path);
        if (options.isFollowLinks() && file.getAttributes().isSymbolicLink()) {
            // XXX follow symlink
        }
        return file;
    }

    /**
     * Resolve local file, without following any symlink.
     * 
     * @param localPath
     *            Non-empty local pathname.
     * @return Local file resolved.
     */
    protected IFile _resolveNoRec(String localPath)
            throws BadPathException, FileResolveException {
        IPath path = parseLocalPath(localPath);
        return _resolveNoRec(path);
    }

    /**
     * Resolve local file, without following any symlink.
     * 
     * @param _path
     *            Parsed local path. Non-<code>null</code>.
     * @return Local file resolved.
     */
    protected abstract IFile _resolveNoRec(IPath _path)
            throws FileResolveException;

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
