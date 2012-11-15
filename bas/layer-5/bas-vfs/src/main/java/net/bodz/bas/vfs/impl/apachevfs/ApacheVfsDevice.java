package net.bodz.bas.vfs.impl.apachevfs;

import org.apache.commons.vfs.FileName;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;

import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFSException;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathFormat;

public class ApacheVfsDevice
        extends AbstractVfsDevice {

    private final FileSystemManager fileSystemManager;
    private final String scheme;

    ApacheFile rootFile = null;

    public ApacheVfsDevice(ApacheVfsDriver driver, String scheme) {
        super(driver, scheme, null);
        this.fileSystemManager = driver.getFileSystemManager();
        this.scheme = scheme;
    }

    public FileSystemManager getFileSystemManager() {
        return fileSystemManager;
    }

    public String getScheme() {
        return scheme;
    }

    /**
     * It's impossible to get the parent layer in Apache VFS, through this delegate.
     */
    @Override
    public IFile getDeviceFile() {
        return null;
    }

    @Override
    public ApacheFile getRootFile() {
        return rootFile;
    }

    @Override
    public ApachePath parse(String localPath)
            throws BadPathException {
        String qPath = localPath;
        String fqPath = getProtocol() + ":" + qPath;

        FileName fileName;
        try {
            fileName = fileSystemManager.resolveURI(fqPath);
        } catch (FileSystemException e) {
            throw new BadPathException(e.getMessage(), e);
        }

        ApachePath path = new ApachePath(fileName);
        return path;
    }

    ApacheFile _resolveUri(String fqPath)
            throws BadPathException, FileResolveException {
        FileObject fileObject;
        try {
            fileObject = fileSystemManager.resolveFile(fqPath);
        } catch (FileSystemException e) {
            throw new FileResolveException(e.getMessage(), e);
        }
        return new ApacheFile(this, fileObject);
    }

    @Override
    public ApacheFile resolve(String localPath)
            throws BadPathException, FileResolveException {
        String qPath = localPath;
        String fqPath = getProtocol() + ":" + qPath;
        return _resolveUri(fqPath);
    }

    @Override
    public ApacheFile resolve(IPath _path)
            throws FileResolveException {
        ApachePath path = (ApachePath) _path;
        String fqPath = path.toString();
        return _resolveUri(fqPath);
    }

    @Override
    public String format(String localPath, PathFormat pathFormat) {
        String uri = localPath;
        try {
            FileName fileName = fileSystemManager.resolveURI(uri);
            if (pathFormat.isDisplaySafe())
                return fileName.getFriendlyURI();
            else
                return fileName.toString();
        } catch (FileSystemException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean rename(String localPathFrom, String localPathTo)
            throws BadPathException {
        ApacheFile fileFrom = resolve(localPathFrom);
        ApacheFile fileTo = resolve(localPathTo);
        FileObject objFrom = fileFrom.getFileObject();
        FileObject objTo = fileTo.getFileObject();

        if (!objFrom.canRenameTo(objTo))
            return false;

        try {
            objFrom.moveTo(objTo);
            return true;
        } catch (FileSystemException e) {
            throw new VFSException("Failed to move", e);
        }
    }

}
