package net.bodz.bas.vfs.impl.mem;

import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class MemoryVfsDevice
        extends AbstractVfsDevice {

    public MemoryVfsDevice(MemoryVfsDriver driver, String scopeName) {
        super(driver, driver.protocol, scopeName);
    }

    public String getScopeName() {
        return getDeviceSpec();
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
    public boolean rename(String localPathFrom, String localPathTo)
            throws BadPathException {
        return false;
    }

}
