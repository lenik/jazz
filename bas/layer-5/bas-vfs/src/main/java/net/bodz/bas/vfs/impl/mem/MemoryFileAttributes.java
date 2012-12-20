package net.bodz.bas.vfs.impl.mem;

import net.bodz.bas.t.buffer.IMovableBuffer;
import net.bodz.bas.vfs.inode.Inode;
import net.bodz.bas.vfs.inode.InodeFileAttributes;

public class MemoryFileAttributes
        extends InodeFileAttributes {

    MemoryFile file;

    public MemoryFileAttributes(MemoryFile file) {
        this.file = file;
    }

    @Override
    protected Inode getInode() {
        // createInode?...
        return file.getInode();
    }

    @Override
    protected long getDataSize(Object data) {
        IMovableBuffer buffer = (IMovableBuffer) data;
        return buffer.size();
    }

    @Override
    protected Inode resolveSymlink(String targetSpec) {
        MemoryFile targetFile = (MemoryFile) file.resolve(targetSpec);
        return targetFile.getInode();
    }

}
