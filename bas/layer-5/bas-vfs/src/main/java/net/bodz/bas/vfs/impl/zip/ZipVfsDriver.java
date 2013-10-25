package net.bodz.bas.vfs.impl.zip;

import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.vfs.AbstractVfsDriver;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.FileResolveOptions;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

/**
 * zip:zip:/foo.zip:/bar.zip:/file
 */
public class ZipVfsDriver
        extends AbstractVfsDriver {

    final String protocol;
    final Map<String, ZipVfsDevice> deviceMap;

    public ZipVfsDriver(String protocol) {
        this.protocol = protocol;
        deviceMap = new LinkedHashMap<String, ZipVfsDevice>();
    }

    @Override
    public void configure(IFileSystem system) {
        system.addDriver(protocol, this);
    }

    ZipVfsDevice resolveDevice(String zipName)
            throws FileResolveException {
        zipName = _normalizeZipName(zipName);
        ZipVfsDevice device = deviceMap.get(zipName);

        if (device != null)
            if (device.isMediaRemoved())
                device = null;

        if (device == null) {
            IFile deviceFile = VFS.resolve(zipName);
            device = new ZipVfsDevice(this, deviceFile, zipName);
            deviceMap.put(zipName, device);
        }

        return device;
    }

    @Override
    protected ZipEntryPath _parse(String protocol, String _path)
            throws BadPathException {
        // Ignore the protocol.

        int colon = _path.lastIndexOf(':');
        assert colon != -1;

        String deviceName;
        String localPath;

        deviceName = _path.substring(0, colon);
        localPath = _path.substring(colon + 1);

        ZipVfsDevice device = resolveDevice(deviceName);

        return device.parseLocalPath(localPath);
    }

    @Override
    public ZipEntryFile resolve(IPath path, FileResolveOptions options)
            throws FileResolveException {

        String deviceName = path.getDeviceSpec();
        ZipVfsDevice device = resolveDevice(deviceName);

        ZipEntryFile file = (ZipEntryFile) device.resolve(path.getLocalPath());

        return file;
    }

    private static final ZipVfsDriver instance = new ZipVfsDriver("zip");

    public static ZipVfsDriver getInstance() {
        return instance;
    }

    static String _normalizeZipName(String zipName) {
        return zipName;
    }

}
