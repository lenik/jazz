package net.bodz.bas.vfs.impl.pseudo;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.io.res.builtin.CharsResource;
import net.bodz.bas.vfs.IFsObject;

public class CharsFile
        extends PseudoFile {

    private final CharsResource _resource;

    public CharsFile() {
        this("(no name)", new CharsResource());
    }

    public CharsFile(String name, CharsResource resource) {
        super(name, resource);
        this._resource = resource;
        setReadable(true);
        setWritable(true);
    }

    /** ⇱ Implementation Of {@link IFsObject}. */
    /* _____________________________ */static section.iface __FS_OBJ__;

    @Override
    public Boolean exists() {
        return _resource.isAllocated();
    }

    @Override
    public int delete(DeleteOption... options) {
        if (_resource.isAllocated()) {
            _resource.unallocate();
            return 1;
        }
        else {
            return 0;
        }
    }

}
