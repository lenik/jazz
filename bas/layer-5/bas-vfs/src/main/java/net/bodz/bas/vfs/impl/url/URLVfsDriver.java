package net.bodz.bas.vfs.impl.url;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.vfs.AbstractVfsDriver;
import net.bodz.bas.vfs.FileResolveOptions;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class URLVfsDriver
        extends AbstractVfsDriver {

    private Map<String, URLVfsDevice> nsMap;

    public URLVfsDriver() {
        nsMap = new HashMap<String, URLVfsDevice>();
    }

    @Override
    public void configure(IFileSystem pathSystem) {
        pathSystem.addGenericDriver(this, NORMAL_PRIORITY);
    }

    @Override
    public boolean accepts(String protocol) {
        return URLSchemes.getSchemeNames().contains(protocol);
    }

    @Override
    protected URLPath _parse(String protocol, String qPath)
            throws BadPathException {

        String deviceSpec;
        String localPath;

        URLFormat format = URLSchemes.getSchemeFormat(protocol);
        switch (format) {
        case plain:
            deviceSpec = ""; // An empty string, to include the device separator.
            while (qPath.startsWith("/"))
                qPath = qPath.substring(1);
            localPath = qPath;
            break;

        case hostPathQuery:
            if (!qPath.startsWith("//"))
                throw new BadPathException("Path doesn't starts with //" + qPath);
            int slash = qPath.indexOf('/', 2);
            if (slash == -1) { // http://example.bar
                deviceSpec = qPath;
                localPath = "";
            } else { // http://example.com/...
                deviceSpec = qPath.substring(0, slash);
                localPath = qPath.substring(slash + 1);
            }
            break;

        case hostQuery:
            // mailto:user@example.com?subject=...
            deviceSpec = null;
            localPath = qPath;
            break;

        case nestedPath:
            // jar:xxxxxxxxxx!yyyyyy
            int excla = qPath.lastIndexOf('!');
            if (excla == -1) {
                deviceSpec = qPath;
                localPath = "";
            } else {
                deviceSpec = qPath.substring(0, excla);
                localPath = qPath.substring(excla);
                while (localPath.startsWith("/"))
                    localPath = localPath.substring(1);
            }
            break;

        default:
            throw new UnexpectedException("Undefined URL format: " + format);
        }

        URLVfsDevice device = getDevice(protocol, deviceSpec);
        return device.parseLocalPath(localPath);
    }

    @Override
    public URLFile resolve(IPath _path, FileResolveOptions options) {
        URLPath path = (URLPath) _path;
        String deviceSpec = path.getDeviceSpec();
        URLVfsDevice device = getDevice(path.getProtocol(), deviceSpec);
        URLFile file = new URLFile(device, path);
        return file;
    }

    public synchronized URLVfsDevice getDevice(String protocol, String deviceSpec) {
        String ns = protocol + ":" + deviceSpec;
        URLVfsDevice device = nsMap.get(ns);
        if (device == null) {
            device = new URLVfsDevice(this, protocol, deviceSpec);
            nsMap.put(ns, device);
        }
        return device;
    }

    private static final URLVfsDriver instance = new URLVfsDriver();

    public static URLVfsDriver getInstance() {
        return instance;
    }

}
