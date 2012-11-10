package net.bodz.bas.vfs.impl.mem;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.vfs.AbstractVfsDriver;
import net.bodz.bas.vfs.path.IPathSystem;

public class MemoryVfsDriver
        extends AbstractVfsDriver {

    Map<String, MemoryVfsDevice> devices;

    public MemoryVfsDriver() {
        devices = new HashMap<String, MemoryVfsDevice>();
    }

    @Override
    public void configure(IPathSystem pathSystem) {
        MemoryPathParser parser = new MemoryPathParser(this);
        pathSystem.addPathParser("mem", parser);
    }

    public synchronized MemoryVfsDevice getDevice(String session) {
        MemoryVfsDevice device = devices.get(session);
        if (device == null) {
            device = new MemoryVfsDevice(this);
            devices.put(session, device);
        }
        return device;
    }

}
