package net.bodz.bas.vfs.impl.apachevfs;

import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.VFS;

import net.bodz.bas.err.IllegalConfigException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.vfs.AbstractVfsDriver;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IVfsDevice;

/**
 * Apache-VFS File System.
 */
public class ApacheVfsDriver
        extends AbstractVfsDriver {

    private final FileSystemManager fileSystemManager;

    public ApacheVfsDriver()
            throws FileSystemException {
        this(VFS.getManager());
    }

    public ApacheVfsDriver(FileSystemManager manager) {
        if (manager == null)
            throw new NullPointerException("manager");
        this.fileSystemManager = manager;
    }

    @Override
    public IVfsDevice getDevice(IFile deviceFile) {
        if (deviceFile != null)
            throw new IllegalUsageException("Device file is not applicable for Apache VFS device.");
        return ApacheVfsDevice.getInstance();
    }

    public FileSystemManager getFileSystemManager() {
        return fileSystemManager;
    }

    private static ApacheVfsDriver driver;

    public static synchronized ApacheVfsDriver getInstance() {
        if (driver == null)
            try {
                driver = new ApacheVfsDriver();
            } catch (FileSystemException e) {
                throw new IllegalConfigException(e.getMessage(), e);
            }
        return driver;
    }

}
