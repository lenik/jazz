package net.bodz.bas.vfs.impl.filter;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.path.AbstractPath;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class FilterPath
        extends AbstractPath {

    private static final long serialVersionUID = 1L;
    public static final String SCOPE_SEPARATOR = ":";

    String protocol;
    String filterSpec;
    String encodedPath;

    public FilterPath(String protocol, String filterSpec, String encodedPath) {
        if (filterSpec == null)
            throw new NullPointerException("filterSpec");
        if (encodedPath == null)
            throw new NullPointerException("encodedPath");
        this.protocol = protocol;
        this.filterSpec = filterSpec;
        this.encodedPath = encodedPath;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public String getDeviceSpec() {
        return filterSpec;
    }

    @Override
    public String getLocalPath() {
        return encodedPath;
    }

    @Override
    public String[] getLocalEntries() {
        return StringArray.splitRaw(encodedPath, "/");
    }

    @Override
    public IPath getParentLayer() {
        IFileSystem system = VFS.getFileSystem(); // parameter?
        FilterVfsDriver driver = (FilterVfsDriver) system.getDriver(protocol);
        FilterVfsDevice device = driver.getDevice(filterSpec);
        IVfsFilter filter = device.getFilter();
        IPath destPath = filter.decodePath(encodedPath);
        return destPath;
    }

    @Override
    protected FilterPath createLocal(String localPath)
            throws BadPathException {
        return new FilterPath(protocol, filterSpec, localPath);
    }

}
