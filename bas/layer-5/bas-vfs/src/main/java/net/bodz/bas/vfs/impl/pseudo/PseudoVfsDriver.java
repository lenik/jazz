package net.bodz.bas.vfs.impl.pseudo;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.MultiSessionVfsDriver;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

/**
 * pseudo:session/local-path
 */
public class PseudoVfsDriver
        extends MultiSessionVfsDriver {

    public static final String DEFAULT_SESSION = "default";

    String protocol;
    PseudoVfsDevice defaultDevice;
    Map<String, PseudoVfsDevice> devices;

    public PseudoVfsDriver(String protocol) {
        this.protocol = protocol;
        devices = new HashMap<String, PseudoVfsDevice>();
        defaultDevice = new PseudoVfsDevice(this, DEFAULT_SESSION, protocol);
        devices.put(DEFAULT_SESSION, defaultDevice);
    }

    @Override
    public void configure(IFileSystem system) {
        system.addDriver(protocol, this);
    }

    public synchronized PseudoVfsDevice getDevice(String sessionName) {
        PseudoVfsDevice device = devices.get(sessionName);
        if (device == null) {
            device = new PseudoVfsDevice(this, sessionName, protocol);
            devices.put(sessionName, device);
        }
        return device;
    }

    public PseudoVfsDevice getDefaultDevice() {
        return defaultDevice;
    }

    @Override
    protected IPath parse(String session, String path)
            throws BadPathException {
        PseudoVfsDevice device = getDevice(session);
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
