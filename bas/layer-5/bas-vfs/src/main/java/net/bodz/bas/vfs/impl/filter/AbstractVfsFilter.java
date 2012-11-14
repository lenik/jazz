package net.bodz.bas.vfs.impl.filter;

import java.io.InputStream;
import java.io.OutputStream;

import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.path.IPath;

public abstract class AbstractVfsFilter
        implements IVfsFilter {

    IFileSystem system;

    public AbstractVfsFilter() {
        this(VFS.getFileSystem());
    }

    public AbstractVfsFilter(IFileSystem system) {
        if (system == null)
            throw new NullPointerException("system");
        this.system = system;
    }

    @Override
    public String encodePath(IPath path) {
        return path.toString();
    }

    @Override
    public IPath decodePath(String encodedPath) {
        return system.parse(encodedPath);
    }

    @Override
    public OutputStream encodeData(OutputStream decodedOutput) {
        return decodedOutput;
    }

    @Override
    public InputStream decodeData(InputStream encodedInput) {
        return encodedInput;
    }

}
