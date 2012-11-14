package net.bodz.bas.vfs.impl.filter;

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
    String filterDescriptor;
    String encodedPath;

    public FilterPath(String protocol, String filterDescriptor, String encodedPath) {
        if (filterDescriptor == null)
            throw new NullPointerException("filterDescriptor");
        if (encodedPath == null)
            throw new NullPointerException("encodedPath");
        this.protocol = protocol;
        this.filterDescriptor = filterDescriptor;
        this.encodedPath = encodedPath;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public String getScopeName() {
        return filterDescriptor;
    }

    @Override
    public String getLocalPath() {
        return encodedPath;
    }

    @Override
    public String[] getLocalEntries() {
        return encodedPath.split("/");
    }

    @Override
    public IPath getParentLayer() {
        IFileSystem system = VFS.getFileSystem(); // parameter?
        FilterVfsDriver driver = (FilterVfsDriver) system.getDriver(protocol);
        FilterVfsDevice device = driver.getDevice(filterDescriptor);
        IVfsFilter filter = device.getFilter();
        IPath destPath = filter.decodePath(encodedPath);
        return destPath;
    }

    @Override
    protected FilterPath createLocal(String localPath)
            throws BadPathException {
        return new FilterPath(protocol, filterDescriptor, localPath);
    }

}
