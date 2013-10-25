package net.bodz.bas.vfs.impl.pseudo;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.io.res.builtin.CharsResource;
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
        setReadable(true);
        setWritable(true);
    }

    /** ⇱ Implementation Of {@link IFsObject}. */
    /* _____________________________ */static section.iface __FS_OBJ__;

    @Override
    public Boolean exists() {
        return resource.isAllocated();
    }

    @Override
    public int delete(DeleteOption... options) {
        if (resource.isAllocated()) {
            resource.unallocate();
            return 1;
        } else {
            return 0;
        }
    }

}
