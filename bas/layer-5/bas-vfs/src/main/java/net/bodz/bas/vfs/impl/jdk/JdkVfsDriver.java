package net.bodz.bas.vfs.impl.jdk;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.vfs.AbstractVfsDriver;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IVfsDevice;

public class JdkVfsDriver
        extends AbstractVfsDriver {

    @Override
    public IVfsDevice getDevice(IFile deviceFile) {
        if (deviceFile != null)
            throw new IllegalUsageException("Device file is not applicable for JDK VFS device.");
        return JdkVfsDevice.getInstance();
    }

    private static final JdkVfsDriver instance = new JdkVfsDriver();

    public static JdkVfsDriver getInstance() {
        return instance;
    }

}
