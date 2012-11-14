package net.bodz.bas.vfs.impl.mem;

import java.nio.charset.Charset;

import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFSException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.util.IFileFilter;
import net.bodz.bas.vfs.util.IFilenameFilter;

public class MemoryFile
        extends AbstractFile {

    private MemoryVfsDevice device;

    public MemoryFile(MemoryVfsDevice device, String baseName) {
        super(device, baseName);
        this.device = device;
    }

    @Override
    public MemoryFile clone() {
        return new MemoryFile(device, getName());
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
    public IStreamResource getResource(Charset charset) {

        return null;
    }

}
