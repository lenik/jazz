package net.bodz.bas.vfs.impl.jdk;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.vfs.AbstractVfsDriver;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class URLVfsDriver
        extends AbstractVfsDriver {

    /**
     * These protocol names are found in the package name:
     * 
     * sun.net.www.protocol.*.Handler
     */
    private transient String[] systemSupportedProtocols;
    private Set<String> protocols = new HashSet<String>();

    private Map<String, URLVfsDevice> nsMap;

    public URLVfsDriver() {
        systemSupportedProtocols = new String[] { "file", "ftp", "gopher", "http", "https", "jar", "mailto", "netdoc" };
        Collections.addAll(protocols, systemSupportedProtocols);

        nsMap = new HashMap<String, URLVfsDevice>();
    }

    @Override
    public void configure(IFileSystem pathSystem) {
        pathSystem.addGenericDriver(this, NORMAL_PRIORITY);
    }

    @Override
    public boolean accepts(String protocol) {
        return protocols.contains(protocol);
    }

    @Override
    public URLPath parse(String fqPath)
            throws BadPathException {
        return (URLPath) super.parse(fqPath);
    }

    @Override
    protected URLPath _parse(String protocol, String qPath)
            throws BadPathException {
        String hostspec;
        String localPath;

        assert qPath.startsWith("//");
        int slash = qPath.indexOf('/', 2);

        if (slash == -1) {
            hostspec = qPath;
            localPath = "";
        } else {
            hostspec = qPath.substring(0, slash);
            localPath = qPath.substring(slash + 1);
        }

        URLVfsDevice device = getDevice(protocol, hostspec);
        return device.parse(localPath);
    }

    @Override
    public URLFile resolve(IPath _path)
            throws FileResolveException {
        return null;
    }

    public synchronized URLVfsDevice getDevice(String protocol, String hostspec) {
        String ns = protocol + ":" + hostspec;
        URLVfsDevice device = nsMap.get(ns);
        if (device == null) {
            device = new URLVfsDevice(this, hostspec, protocol);
            nsMap.put(ns, device);
        }
        return device;
    }

    private static final URLVfsDriver instance = new URLVfsDriver();

    public static URLVfsDriver getInstance() {
        return instance;
    }

}
