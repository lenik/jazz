package net.bodz.bas.vfs.impl.mem;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.MultiSessionVfsDriver;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

/**
 * mem:session/local-path
 */
public class MemoryVfsDriver
        extends MultiSessionVfsDriver {

    String protocol;
    Map<String, MemoryVfsDevice> devices;

    public MemoryVfsDriver(String protocol) {
        this.protocol = protocol;
        this.devices = new HashMap<String, MemoryVfsDevice>();
    }

    @Override
    public void configure(IFileSystem system) {
        system.addDriver(protocol, this);
    }

    public synchronized MemoryVfsDevice getDevice(String session) {
        MemoryVfsDevice device = devices.get(session);
        if (device == null) {
            device = new MemoryVfsDevice(this, session, protocol);
            devices.put(session, device);
        }
        return device;
    }

    @Override
    protected IPath parse(String session, String path)
            throws BadPathException {
        MemoryVfsDevice device = getDevice(session);
        return device.parse(path);
    }

    @Override
    public IFile resolve(IPath path)
            throws FileResolveException {
        String session = path.getDeviceName();
        MemoryVfsDevice device = getDevice(session);
        return device.resolve(path.getLocalPath());
    }

    private static MemoryVfsDriver instance = new MemoryVfsDriver("mem");

    public static MemoryVfsDriver getInstance() {
        return instance;
    }

}
