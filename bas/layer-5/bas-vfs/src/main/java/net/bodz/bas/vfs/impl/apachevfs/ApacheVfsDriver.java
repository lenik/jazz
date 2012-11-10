package net.bodz.bas.vfs.impl.apachevfs;

import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.VFS;

import net.bodz.bas.err.IllegalConfigException;
import net.bodz.bas.vfs.AbstractVfsDriver;
import net.bodz.bas.vfs.path.IGenericPathParser;
import net.bodz.bas.vfs.path.IPathSystem;

/**
 * Apache-VFS File System.
 */
public class ApacheVfsDriver
        extends AbstractVfsDriver {

    private FileSystemManager fileSystemManager;
    private ApacheVfsDevice device;

    public ApacheVfsDriver()
            throws FileSystemException {
        this(VFS.getManager());
    }

    public ApacheVfsDriver(FileSystemManager manager) {
        if (manager == null)
            throw new NullPointerException("manager");
        this.fileSystemManager = manager;
        this.device = new ApacheVfsDevice(this);
    }

    public FileSystemManager getFileSystemManager() {
        return fileSystemManager;
    }

    @Override
    public void configure(IPathSystem pathSystem) {
        ApachePathParser parser = new ApachePathParser(this);
        pathSystem.addGenericPathParser(parser, IGenericPathParser.LOW_PRIORITY);
    }

    public ApacheVfsDevice getDevice() {
        return device;
    }

    private static ApacheVfsDriver instance;

    public static synchronized ApacheVfsDriver getInstance() {
        if (instance == null)
            try {
                instance = new ApacheVfsDriver();
            } catch (FileSystemException e) {
                throw new IllegalConfigException(e.getMessage(), e);
            }
        return instance;
    }

}
