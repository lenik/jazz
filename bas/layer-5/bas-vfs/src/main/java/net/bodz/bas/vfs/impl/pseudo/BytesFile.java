package net.bodz.bas.vfs.impl.pseudo;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.io.res.builtin.BytesResource;

public class BytesFile
        extends PseudoFile {

    private BytesResource resource;

    public BytesFile() {
        this("(no name)", new BytesResource());
    }

    public BytesFile(String name, BytesResource resource) {
        super(name, resource);
        this.resource = resource;

        setReadable(true);
        setWritable(true);
    }

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
