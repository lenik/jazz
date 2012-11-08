package net.bodz.bas.vfs.impl.pseudo;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.vfs.AbstractVfsDriver;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IVfsDevice;

public class PseudoVfsDriver
        extends AbstractVfsDriver {

    @Override
    public IVfsDevice getDevice(IFile deviceFile) {
        if (deviceFile != null)
            throw new IllegalUsageException("Device file is not applicable for Pseudo VFS device.");
        return PseudoVfsDevice.getInstance();
    }

    private static final PseudoVfsDriver instance = new PseudoVfsDriver();

    public static PseudoVfsDriver getInstance() {
        return instance;
    }

}
