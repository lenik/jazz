package net.bodz.bas.vfs.impl.filter;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.NotLinkException;

import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class FilterVfsDevice
        extends AbstractVfsDevice {

    private IVfsFilter filter;

    public FilterVfsDevice(FilterVfsDriver driver, String filterSpec) {
        super(driver, driver.protocol, filterSpec);
    }

    public String getFilterSpec() {
        return getDeviceSpec();
    }

    public IVfsFilter getFilter() {
        return filter;
    }

    @Override
    public IFile getRootFile() {
        return null;
    }

    @Override
    public FilterPath parse(String localPath)
            throws BadPathException {
        return null;
    }

    @Override
    public IFile resolve(IPath _path)
            throws FileResolveException {
        return null;
    }

    @Override
    public boolean move(String localPathFrom, String localPathTo, CopyOption... options)
            throws BadPathException {
        return false;
    }

    @Override
    public boolean createLink(String localPath, String target, boolean symbolic)
            throws IOException {
        return false;
    }

    @Override
    public String readSymbolicLink(String localPath)
            throws NotLinkException, IOException {
        return null;
    }

}
