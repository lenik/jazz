package net.bodz.bas.vfs.impl.pseudo;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.ScopedVfsDriver;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

/**
 * pseudo:scope/local-path
 */
public class PseudoVfsDriver
        extends ScopedVfsDriver {

    public static final String DEFAULT_SCOPE = "default";

    String protocol;
    PseudoVfsDevice defaultDevice;
    Map<String, PseudoVfsDevice> scopeDeviceMap;

    public PseudoVfsDriver(String protocol) {
        this.protocol = protocol;
        scopeDeviceMap = new HashMap<String, PseudoVfsDevice>();
        defaultDevice = new PseudoVfsDevice(this, DEFAULT_SCOPE, protocol);
        scopeDeviceMap.put(DEFAULT_SCOPE, defaultDevice);
    }

    @Override
    public void configure(IFileSystem system) {
        system.addDriver(protocol, this);
    }

    public synchronized PseudoVfsDevice getDevice(String scopeName) {
        PseudoVfsDevice device = scopeDeviceMap.get(scopeName);
        if (device == null) {
            device = new PseudoVfsDevice(this, scopeName, protocol);
            scopeDeviceMap.put(scopeName, device);
        }
        return device;
    }

    public PseudoVfsDevice getDefaultDevice() {
        return defaultDevice;
    }

    @Override
    protected String getScopeSeparator() {
        return PseudoPath.SCOPE_SEPARATOR;
    }

    @Override
    protected IPath parse(String scope, String path)
            throws BadPathException {
        PseudoVfsDevice device = getDevice(scope);
        return device.parse(path);
    }

    @Override
    public IFile resolve(IPath path)
            throws FileResolveException {
        return null;
    }

    private static final PseudoVfsDriver instance = new PseudoVfsDriver("pseudo");

    public static PseudoVfsDriver getInstance() {
        return instance;
    }

}
