package net.bodz.bas.vfs.impl.mem;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.ScopedVfsDriver;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

/**
 * mem:scope:local-path
 */
public class MemoryVfsDriver
        extends ScopedVfsDriver {

    String protocol;
    Map<String, MemoryVfsDevice> scopeDeviceMap;

    public MemoryVfsDriver(String protocol) {
        this.protocol = protocol;
        this.scopeDeviceMap = new HashMap<String, MemoryVfsDevice>();
    }

    @Override
    public void configure(IFileSystem system) {
        system.addDriver(protocol, this);
    }

    public synchronized MemoryVfsDevice getDevice(String scopeName) {
        MemoryVfsDevice device = scopeDeviceMap.get(scopeName);
        if (device == null) {
            device = new MemoryVfsDevice(this, scopeName);
            scopeDeviceMap.put(scopeName, device);
        }
        return device;
    }

    @Override
    protected String getScopeSeparator() {
        return MemoryPath.SCOPE_SEPARATOR;
    }

    @Override
    protected MemoryPath parse(String scope, String path)
            throws BadPathException {
        MemoryVfsDevice device = getDevice(scope);
        return device.parse(path);
    }

    @Override
    public IFile resolve(IPath _path)
            throws FileResolveException {
        MemoryPath path = (MemoryPath) _path;
        String scope = path.getDeviceSpec();
        MemoryVfsDevice device = getDevice(scope);
        return device.resolve(path);
    }

    private static MemoryVfsDriver instance = new MemoryVfsDriver("mem");

    public static MemoryVfsDriver getInstance() {
        return instance;
    }

}
