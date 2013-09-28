package net.bodz.bas.vfs.impl.apache;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.NotLinkException;

import org.apache.commons.vfs.FileName;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFSException;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

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
    public ApacheFile _resolveNoRec(String localPath)
            throws BadPathException, FileResolveException {
        String qPath = localPath;
        String fqPath = getProtocol() + ":" + qPath;
        return _resolveUri(fqPath);
    }

    @Override
    public ApacheFile _resolveNoRec(IPath _path)
            throws FileResolveException {
        ApachePath path = (ApachePath) _path;
        String fqPath = path.toString();
        return _resolveUri(fqPath);
    }

    @Override
    public boolean move(String localPathFrom, String localPathTo, CopyOption... options)
            throws BadPathException {
        if (localPathFrom == null)
            throw new NullPointerException("localPathFrom");
        if (localPathTo == null)
            throw new NullPointerException("localPathTo");

        if (localPathFrom.equals(localPathTo))
            return true;

        ApacheFile src = _resolveNoRec(localPathFrom);
        ApacheFile dest = _resolveNoRec(localPathTo);

        FileObject srcObj = src.getFileObject();
        FileObject destObj = dest.getFileObject();

        if (!srcObj.canRenameTo(destObj))
            return false;

        try {
            srcObj.moveTo(destObj);
            return true;
        } catch (FileSystemException e) {
            throw new VFSException("Failed to move", e);
        }
    }

    @Override
    public boolean createLink(String localPath, String target, boolean symbolic)
            throws IOException {
        if (localPath == null)
            throw new NullPointerException("localPath");
        if (target == null)
            throw new NullPointerException("target");

        ApacheFile linkFile = _resolveNoRec(localPath);
        if (linkFile.exists() == Boolean.TRUE)
            return false;

        FileObject linkObj = linkFile.getFileObject();

        throw new NotImplementedException();
    }

    @Override
    public String readSymbolicLink(String localPath)
            throws NotLinkException, IOException {
        throw new NotImplementedException();
    }

}
