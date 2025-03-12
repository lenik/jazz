package net.bodz.bas.vfs.impl.apache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.VFS;

import net.bodz.bas.c.java.util.Sets;
import net.bodz.bas.err.IllegalConfigException;
import net.bodz.bas.vfs.AbstractVfsDriver;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.FileResolveOptions;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

/**
 * Apache-VFS File System.
 */
public class ApacheVfsDriver
        extends AbstractVfsDriver {

    private FileSystemManager fileSystemManager;
    private Set<String> supportedSchemes;
    private Map<String, ApacheVfsDevice> schemeMap;

    public ApacheVfsDriver(FileSystemManager manager) {
        if (manager == null)
            throw new NullPointerException("manager");
        this.fileSystemManager = manager;
        this.supportedSchemes = Collections.unmodifiableSet(Sets.toHashSet( //
                manager.getSchemes()));
        this.schemeMap = new HashMap<String, ApacheVfsDevice>();
    }

    public FileSystemManager getFileSystemManager() {
        return fileSystemManager;
    }

    @Override
    public void configure(IFileSystem system) {
        system.addGenericDriver(this, LOW_PRIORITY);
    }

    @Override
    public boolean accepts(String protocol) {
        return supportedSchemes.contains(protocol);
    }

    @Override
    protected ApachePath _parse(String protocol, String qPath)
            throws BadPathException {
        ApacheVfsDevice device = getDevice(protocol);
        return device.parseLocalPath(qPath);
    }

    @Override
    public IFile resolve(IPath _path, FileResolveOptions options)
            throws FileResolveException {
        ApachePath path = (ApachePath) _path;
        String scheme = path.getProtocol();
        ApacheVfsDevice device = getDevice(scheme);
        return device.resolve(path, options);
    }

    public synchronized ApacheVfsDevice getDevice(String scheme) {
        ApacheVfsDevice device = schemeMap.get(scheme);
        if (device == null) {
            device = new ApacheVfsDevice(this, scheme);
            schemeMap.put(scheme, device);
        }
        return device;
    }

    private static ApacheVfsDriver instance;

    public static synchronized ApacheVfsDriver getInstance() {
        if (instance == null) {
            FileSystemManager manager;
            try {
                manager = VFS.getManager();
            } catch (FileSystemException e) {
                throw new IllegalConfigException(e.getMessage(), e);
            }
            instance = new ApacheVfsDriver(manager);
        }
        return instance;
    }

}
