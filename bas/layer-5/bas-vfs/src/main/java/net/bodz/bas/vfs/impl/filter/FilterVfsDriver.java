package net.bodz.bas.vfs.impl.filter;

import java.util.Map;

import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.FileResolveOptions;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.ScopedVfsDriver;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class FilterVfsDriver
        extends ScopedVfsDriver {

    String protocol;
    Map<String, FilterVfsDevice> map;

    public FilterVfsDriver(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public void configure(IFileSystem system) {
        system.addDriver(protocol, this);
    }

    @Override
    protected FilterPath parse(String scope, String localPath)
            throws BadPathException {
        return null;
    }

    @Override
    public IFile resolve(IPath _path, FileResolveOptions options)
            throws FileResolveException {
        return null;
    }

    public FilterVfsDevice getDevice(String filterDescriptor) {
        return null;
    }

}
