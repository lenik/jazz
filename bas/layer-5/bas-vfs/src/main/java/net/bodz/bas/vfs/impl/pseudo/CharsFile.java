package net.bodz.bas.vfs.impl.pseudo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.builtin.CharsResource;
import net.bodz.bas.vfs.IFsBlob;
import net.bodz.bas.vfs.IFsObject;

public class CharsFile
        extends PseudoFile {

    private CharsResource resource;

    public CharsFile() {
        this("(no name)", new CharsResource());
    }

    public CharsFile(String name, CharsResource resource) {
        super(name, resource);
        this.resource = resource;
        this.inode.setReadable(true);
        this.inode.setWritable(true);
    }

    /** ⇱ Implementation Of {@link IFsObject}. */
    /* _____________________________ */static section.iface __FS_OBJ__;

    @Override
    public Boolean exists() {
        return resource.isAllocated();
    }

    @Override
    public boolean delete(DeleteOption... options) {
        if (resource.isAllocated()) {
            resource.unallocate();
            return true;
        } else {
            return false;
        }
    }

    /** ⇱ Implementation Of {@link IFsBlob}. */
    /* _____________________________ */static section.iface __BLOB__;

    @Override
    public long getLength() {
        if (resource.isAllocated())
            return resource.getLength();
        else
            return 0;
    }

    @Override
    public IStreamInputSource getInputSource(Charset charset) {
        throw new UnsupportedOperationException("Write-Only");
    }

    /** ⇱ Implementation Of {@link BasicFileAttributes}. */
    /* _____________________________ */static section.iface __ATTRS_BASIC__;

    private long creationTime = System.currentTimeMillis();
    private long lastModifiedTime = creationTime;
    private long lastAccessTime = creationTime;

    @Override
    public FileTime lastModifiedTime() {
        return FileTime.fromMillis(lastModifiedTime);
    }

    @Override
    public FileTime lastAccessTime() {
        return FileTime.fromMillis(lastAccessTime);
    }

    @Override
    public FileTime creationTime() {
        return FileTime.fromMillis(creationTime);
    }

    @Override
    public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime creationTime)
            throws IOException {
        if (lastModifiedTime != null)
            this.lastModifiedTime = lastModifiedTime.toMillis();
        if (lastAccessTime != null)
            this.lastAccessTime = lastAccessTime.toMillis();
        if (creationTime != null)
            this.creationTime = creationTime.toMillis();
    }

}
