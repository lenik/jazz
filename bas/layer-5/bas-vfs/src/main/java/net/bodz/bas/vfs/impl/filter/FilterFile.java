package net.bodz.bas.vfs.impl.filter;

import java.nio.charset.Charset;

import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileFilter;
import net.bodz.bas.vfs.IFilenameFilter;
import net.bodz.bas.vfs.VFSException;
import net.bodz.bas.vfs.path.IPath;

public class FilterFile
        extends AbstractFile {

    public FilterFile(FilterVfsDevice device, String baseName) {
        super(device, baseName);
    }

    @Override
    public IFile getChild(String childName) {
        return null;
    }

    @Override
    public Iterable<? extends IFile> children(IFilenameFilter nameFilter)
            throws VFSException {
        return null;
    }

    @Override
    public Iterable<? extends IFile> children(IFileFilter nameFilter)
            throws VFSException {
        return null;
    }

    @Override
    public IPath getPath() {
        return null;
    }

    @Override
    protected IStreamResource newResource(Charset charset) {
        return null;
    }

    @Override
    public FilterFile clone() {
        return new FilterFile((FilterVfsDevice) getDevice(), getName());
    }

}
