package net.bodz.bas.vfs.impl.mem;

import java.util.List;

import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathFormat;

public class MemoryVfsDevice
        extends AbstractVfsDevice {

    public MemoryVfsDevice(MemoryVfsDriver driver) {
        super(driver);
    }

    @Override
    public List<? extends IFile> getRootFiles() {
        return null;
    }

    @Override
    public IPath parse(String localPath)
            throws BadPathException {
        return null;
    }

    @Override
    public IFile resolve(String localPath)
            throws BadPathException, FileResolveException {
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
