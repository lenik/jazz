package net.bodz.bas.vfs.impl.mem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.OpenOption;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.io.resource.ProxyStreamResource;
import net.bodz.bas.io.resource.builtin.BytesResource;
import net.bodz.bas.io.resource.builtin.CharsResource;
import net.bodz.bas.t.buffer.MovableByteBuffer;
import net.bodz.bas.t.buffer.MovableCharBuffer;
import net.bodz.bas.vfs.inode.Inode;

class MemoryStreamResource
        extends ProxyStreamResource {

    private final MemoryFile file;

    public MemoryStreamResource(MemoryFile file) {
        super(null);
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    @Override
    public IStreamResource getWrapped()
            throws IOException {
        Inode inode = file.getInode();
        if (inode == null)
            // 1. for newOut*, the node is already created before getWrapped()
            // 2. for newIn*, null node means non-exist parent dir.
            throw new FileNotFoundException("File isn't existed: " + file.getPath());

        switch (inode.getType()) {
        case blob:
        case mixed:
            break;

        case none:
        case directory:
        default:
            // 1. for newOut*, the resource is already created before getWrapped()
            // 2. for newIn*, null resource means it's a dir-node or the file isn't created.
            throw new FileNotFoundException("Not a file: " + file.getPath());
        }

        switch (inode.getDataType()) {
        case MemoryFile.MOVABLE_BYTE_BUFFER:
            MovableByteBuffer mbb = (MovableByteBuffer) inode.getData();
            return new BytesResource(mbb);

        case MemoryFile.MOVABLE_CHAR_BUFFER:
            MovableCharBuffer mcb = (MovableCharBuffer) inode.getData();
            return new CharsResource(mcb);

        default:
            throw new UnexpectedException();
        }
    }

    @Override
    protected void beforeOpenOutput(OpenOption... options)
            throws IOException {
        super.beforeOpenOutput(options);

        // file.mkblob(false);

        // OPTIM: should update the time when the output stream is closed.
        file.mkblob(true);
    }

}
