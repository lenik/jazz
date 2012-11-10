package net.bodz.bas.vfs.impl.pseudo;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.vfs.AbstractVfsDriver;
import net.bodz.bas.vfs.path.IPathSystem;

public class PseudoVfsDriver
        extends AbstractVfsDriver {

    PseudoVfsDevice defaultDevice;
    Map<String, PseudoVfsDevice> devices;

    public PseudoVfsDriver() {
        devices = new HashMap<String, PseudoVfsDevice>();
        defaultDevice = new PseudoVfsDevice(this);
        devices.put("default", defaultDevice);
    }

    @Override
    public void configure(IPathSystem pathSystem) {
        PseudoPathParser parser = new PseudoPathParser(this);
        pathSystem.addPathParser("pseudo", parser);
    }

    public synchronized PseudoVfsDevice getDevice(String sessionName) {
        PseudoVfsDevice device = devices.get(sessionName);
        if (device == null) {
            device = new PseudoVfsDevice(this);
            devices.put(sessionName, device);
        }
        return device;
    }

    public PseudoVfsDevice getDefaultDevice() {
        return defaultDevice;
    }

    private static final PseudoVfsDriver instance = new PseudoVfsDriver();

    public static PseudoVfsDriver getInstance() {
        return instance;
    }

}
