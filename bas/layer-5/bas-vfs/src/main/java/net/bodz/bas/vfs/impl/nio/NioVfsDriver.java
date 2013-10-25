package net.bodz.bas.vfs.impl.nio;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Collection;

import net.bodz.bas.c.java.nio.SubPathMap;
import net.bodz.bas.vfs.AbstractVfsDriver;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.FileResolveOptions;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class NioVfsDriver
        extends AbstractVfsDriver {

    final String protocol;
    final SubPathMap<NioVfsDevice> rootMap;

    public NioVfsDriver(String protocol) {
        this.protocol = protocol;

        rootMap = new SubPathMap<>();

        FileSystem fileSystem = FileSystems.getDefault();
        String separator = fileSystem.getSeparator();
        boolean usingSlash = separator.equals("/");

        for (Path rootPath : fileSystem.getRootDirectories()) {
            String rootName = rootPath.toString();

            if (!usingSlash)
                rootName = rootName.replace(separator, "/");
            if (rootName.endsWith("/"))
                rootName = rootName.substring(0, rootName.length() - 1);

            NioVfsDevice rootDevice = new NioVfsDevice(this, rootName, rootPath);
            rootMap.put(rootName, rootDevice);
        }
    }

    @Override
    public void configure(IFileSystem system) {
        system.addDriver(protocol, this);
    }

    @Override
    protected NioPath _parse(String protocol, String _pathstr)
            throws BadPathException {
        // Ignore the protocol.

        String rootName = rootMap.meetKey(_pathstr);
        if (rootName == null)
            throw new BadPathException("Not managed in any root directory: " + _pathstr);

        String localPath = _pathstr.substring(rootName.length() + 1);
        while (localPath.startsWith("/"))
            localPath = localPath.substring(1);

        NioVfsDevice device = getRootDevice(rootName);
        return device.parseLocalPath(localPath);
    }

    @Override
    public NioFile resolve(IPath _path, FileResolveOptions options)
            throws FileResolveException {
        NioPath path = (NioPath) _path;
        String driveName = path.getDeviceSpec();
        NioVfsDevice device = getRootDevice(driveName);
        NioFile file = (NioFile) device.resolve(path, options);
        return file;
    }

    public Collection<NioVfsDevice> getDevices() {
        return rootMap.values();
    }

    public String getRootName(Path _path) {
        String pathstr = _path.toString();
        return rootMap.meetKey(pathstr);
    }

    public NioVfsDevice getRootDevice(Path _path) {
        String rootName = getRootName(_path);
        return getRootDevice(rootName);
    }

    public synchronized NioVfsDevice getRootDevice(String rootName) {
        if (rootName == null)
            throw new NullPointerException("rootName");
        return rootMap.get(rootName);
    }

    private static final NioVfsDriver instance = new NioVfsDriver("file");

    public static NioVfsDriver getInstance() {
        return instance;
    }

}
