package net.bodz.bas.vfs.impl.apachevfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.vfs.FileName;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;

import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFSException;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.PathFormat;

public class ApacheVfsDevice
        extends AbstractVfsDevice {

    private final FileSystemManager fileSystemManager;

    List<ApacheFile> rootFiles = new ArrayList<ApacheFile>();

    public ApacheVfsDevice(ApacheVfsDriver driver) {
        super(driver);
        fileSystemManager = driver.getFileSystemManager();
    }

    public FileSystemManager getFileSystemManager() {
        return fileSystemManager;
    }

    /**
     * It's impossible to get the parent layer in Apache VFS, though this delegate.
     */
    @Override
    public IFile getDeviceFile() {
        return null;
    }

    @Override
    public List<? extends ApacheFile> getRootFiles() {
        return Collections.unmodifiableList(rootFiles);
    }

    @Override
    public ApachePath parse(String localPath)
            throws BadPathException {
        String uri = localPath;
        ApachePath path = new ApachePath(this, uri);
        return path;
    }

    public ApachePath resolve(FileName fileName)
            throws BadPathException {
        String uri = fileName.getURI();
        ApachePath path = new ApachePath(this, uri);
        return path;
    }

    @Override
    public ApacheFile resolve(String localPath)
            throws FileResolveException {
        FileObject fileObject;
        try {
            fileObject = fileSystemManager.resolveFile(localPath);
        } catch (FileSystemException e) {
            throw new FileResolveException("Failed to resolve file", e);
        }
        return new ApacheFile(this, fileObject);
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
