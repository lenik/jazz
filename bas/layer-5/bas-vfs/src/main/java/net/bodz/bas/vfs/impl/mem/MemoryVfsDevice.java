package net.bodz.bas.vfs.impl.mem;

import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IVfsDriver;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathFormat;

public class MemoryVfsDevice
        extends AbstractVfsDevice {

    public MemoryVfsDevice(IVfsDriver driver, String name, String protocol) {
        super(driver, name, protocol);
    }

    @Override
    public IFile getRootFile() {
        return null;
    }

    @Override
    public MemoryPath parse(String localPath)
            throws BadPathException {
        return null;
    }

    @Override
    public MemoryFile resolve(IPath _path)
            throws FileResolveException {
        return null;
    }

    @Override
    public String format(String localPath, PathFormat pathFormat) {
        return null;
    }

    @Override
    public boolean rename(String localPathFrom, String localPathTo)
            throws BadPathException {
        return false;
    }

}
