package net.bodz.bas.vfs.impl.pseudo;

import net.bodz.bas.io.res.IStreamResource;
import net.bodz.bas.vfs.inode.Inode;
import net.bodz.bas.vfs.inode.InodeFileAttributes;

public class PseudoFileAttributes
        extends InodeFileAttributes {

    PseudoFile file;

    public PseudoFileAttributes(PseudoFile file) {
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    @Override
    protected Inode getInode() {
        return file.inode;
    }

    @Override
    protected long getDataSize(Object data) {
        IStreamResource resource = (IStreamResource) data;
        Long length = resource.getLength();
        if (length == null)
            return 0L;
        else
            return length;
    }

}
