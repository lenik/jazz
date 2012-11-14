package net.bodz.bas.vfs.impl.filter;

import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathFormat;

public class FilterVfsDevice
        extends AbstractVfsDevice {

    IVfsFilter filter;

    public FilterVfsDevice(FilterVfsDriver driver, String descriptor, String protocol) {
        super(driver, descriptor, protocol);
    }

    public IVfsFilter getFilter() {
        return filter;
    }

    @Override
    public IFile getRootFile() {
        return null;
    }

    @Override
    public IPath parse(String localPath)
            throws BadPathException {
        return null;
    }

    @Override
    public IFile resolve(IPath _path)
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
