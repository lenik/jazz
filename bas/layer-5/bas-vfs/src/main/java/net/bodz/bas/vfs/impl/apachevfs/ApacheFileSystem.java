package net.bodz.bas.vfs.impl.apachevfs;

import org.apache.commons.vfs.FileName;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.VFS;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.vfs.AbstractFileSystem;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathFormat;

/**
 * Apache-VFS File System.
 */
public class ApacheFileSystem
        extends AbstractFileSystem {

    private final FileSystemManager fileSystemManager;

    public ApacheFileSystem()
            throws FileSystemException {
        this(VFS.getManager());
    }

    public ApacheFileSystem(FileSystemManager manager) {
        if (manager == null)
            throw new NullPointerException("manager");
        this.fileSystemManager = manager;
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
    public IFile[] getRootFiles() {
        throw new NotImplementedException();
    }

    @Override
    public ApacheVFSPath parse(String uri)
            throws BadPathException {
        ApacheVFSPath path = new ApacheVFSPath(this, uri);
        return path;
    }

    public ApacheVFSPath resolve(FileName fileName)
            throws BadPathException {
        String uri = fileName.getURI();
        ApacheVFSPath path = new ApacheVFSPath(this, uri);
        return path;
    }

    @Override
    public ApacheVFSFile resolve(String localPath)
            throws FileResolveException {
        ApacheVFSPath path = parse(localPath);
        try {
            return new ApacheVFSFile(this, path);
        } catch (FileSystemException e) {
            throw new FileResolveException("Failed to resolve file", e);
        }
    }

    @Override
    public String format(IPath path, PathFormat pathFormat) {
        String uri = path.getLocalPath();
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

}
