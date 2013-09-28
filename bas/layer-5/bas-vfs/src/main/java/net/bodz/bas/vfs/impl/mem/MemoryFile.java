package net.bodz.bas.vfs.impl.mem;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.io.res.IRandomResource;
import net.bodz.bas.io.res.builtin.BytesResource;
import net.bodz.bas.t.buffer.IMovableBuffer;
import net.bodz.bas.t.buffer.MovableByteBuffer;
import net.bodz.bas.vfs.*;

public class MemoryFile
        extends MutableFile {

    private MemoryPath path;
    private MovableByteBuffer data;

    public MemoryFile(MemoryVfsDevice device, MemoryPath path) {
        this(device, path, null);
    }

    public MemoryFile(MemoryVfsDevice device, MemoryPath path, MutableFile parentFile) {
        super(device, path.getBaseName(), parentFile);
        this.path = path;
    }

    /** ⇱ Implementation Of {@link IFsObject}. */
    /* _____________________________ */static section.iface __FS_OBJ__;

    @Override
    public MemoryVfsDevice getDevice() {
        return (MemoryVfsDevice) super.getDevice();
    }

    @Override
    public MemoryPath getPath() {
        return path;
    }

    /** ⇱ Implementation Of {@link IFsBlob}. */
/* _____________________________ */static section.iface __BLOB__;

    @Override
    public boolean mkblob(boolean touch)
            throws IOException {

        switch (getType()) {
        case none:
            setType(FsObjectType.blob);
            break;

        case symbolicLink:
            // never try to follow the symlink.
            // mkblob should only returns true if data can be initialized.
            return false;

        case directory:
            // option: enable mixed-mode?
            // setType(FsObjectType.mixed);
            // break;
            return false;

        case blob:
        case mixed:
        }

        if (data == null)
            data = new MovableByteBuffer();

        if (touch)
            touch();

        return true;
    }

    @Override
    public boolean setLength(long newLength)
            throws IOException {
        if (!mkblob(true))
            return false;

        if (newLength > IMovableBuffer.SIZE_MAX)
            throw new OutOfMemoryError();

        int newSize = (int) newLength;
        data.resize(newSize);
        return true;
    }

    @Override
    protected IRandomResource _getResource(Charset charset) {
        BytesResource res = new BytesResource(data);
        // XXX - res.setCharset(charset);
        return res;
    }

    /** ⇱ Implementation Of {@link IFsDir}. */
    /* _____________________________ */static section.iface __DIR__;

    @Override
    public IFile getChild(String childName) {
        MemoryPath childPath = (MemoryPath) path.enter().join(childName);
        return new MemoryFile(getDevice(), childPath);
    }

    @Override
    public boolean mkdir() {
        return mkdirs();
    }

    /** ⇱ Implementation Of {@link IFileAttributes}. */
    /* _____________________________ */static section.iface __ATTRIBUTES__;

}
