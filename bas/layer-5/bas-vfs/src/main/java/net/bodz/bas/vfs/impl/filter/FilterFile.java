package net.bodz.bas.vfs.impl.filter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.attribute.FileTime;

import net.bodz.bas.io.res.IRandomResource;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileAttributes;
import net.bodz.bas.vfs.IFsBlob;
import net.bodz.bas.vfs.path.IPath;

public class FilterFile
        extends AbstractFile {

    public FilterFile(FilterVfsDevice device, String baseName) {
        super(device, baseName);
    }

    /** ⇱ Implementation Of {@link net.bodz.bas.vfs.IFsObject}. */
/* _____________________________ */static section.iface __FS_OBJ__;

    @Override
    public IPath getPath() {
        return null;
    }

    @Override
    public Boolean exists() {
        return null;
    }

    /** ⇱ Implementation Of {@link net.bodz.bas.vfs.IFsDir}. */
    /* _____________________________ */static section.iface __DIR__;

    @Override
    public IFile getChild(String childName) {
        return null;
    }

    /** ⇱ Implementation Of {@link IFsBlob}. */
    /* _____________________________ */static section.iface __BLOB__;

    @Override
    public long getLength()
            throws IOException {
        return 0;
    }

    @Override
    protected IRandomResource _getResource(Charset charset) {
        return null;
    }

    /** ⇱ Implementation Of {@link IFileAttributes}. */
    /* _____________________________ */static section.iface __ATTRIBUTES__;

    @Override
    public FileTime lastModifiedTime() {
        return null;
    }

    /** ⇱ Implementation Of {@link Object}. */
    /* _____________________________ */static section.obj __OBJ__;

    @Override
    public FilterFile clone() {
        return new FilterFile((FilterVfsDevice) getDevice(), getName());
    }

}
