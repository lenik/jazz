package net.bodz.bas.vfs.impl.jdk;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.vfs.AbstractVfsDriver;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IVfsDevice;

public class URLVfsDriver
        extends AbstractVfsDriver {

    @Override
    public IVfsDevice getDevice(IFile deviceFile) {
        if (deviceFile != null)
            throw new IllegalUsageException("Device file is not applicable for URL VFS device.");
        else
            return URLVfsDevice.getInstance();
    }

    private static URLVfsDriver instance = new URLVfsDriver();

    public static URLVfsDriver getInstance() {
        return instance;
    }

    public static void setInstance(URLVfsDriver instance) {
        URLVfsDriver.instance = instance;
    }

}
