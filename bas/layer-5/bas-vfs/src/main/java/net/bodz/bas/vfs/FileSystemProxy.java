package net.bodz.bas.vfs;

import net.bodz.bas.t.model.AbstractProxy;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public abstract class FileSystemProxy
        extends AbstractProxy<IFileSystem>
        implements IFileSystem {

    public FileSystemProxy() {
        super();
    }

    public FileSystemProxy(IFileSystem _orig) {
        super(_orig);
    }

    @Override
    public IVfsDriver getDriver(String protocol) {
        return getWrapped().getDriver(protocol);
    }

    @Override
    public boolean addDriver(String protocol, IVfsDriver driver) {
        return getWrapped().addDriver(protocol, driver);
    }

    @Override
    public void removeDriver(String protocol) {
        getWrapped().removeDriver(protocol);
    }

    @Override
    public void addGenericDriver(IVfsDriver driver, int priority) {
        getWrapped().addGenericDriver(driver, priority);
    }

    @Override
    public void removeGenericDriver(IVfsDriver driver) {
        getWrapped().removeGenericDriver(driver);
    }

    @Override
    public IPath getContextPath() {
        return getWrapped().getContextPath();
    }

    @Override
    public void setContextPath(IPath path) {
        getWrapped().setContextPath(path);
    }

    @Override
    public IPath parse(String path)
            throws BadPathException {
        return getWrapped().parse(path);
    }

    @Override
    public IFile resolve(IPath path)
            throws FileResolveException {
        return getWrapped().resolve(path);
    }

}
