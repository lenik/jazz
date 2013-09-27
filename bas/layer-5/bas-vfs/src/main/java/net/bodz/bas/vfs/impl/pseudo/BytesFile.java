package net.bodz.bas.vfs.impl.pseudo;

import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.io.res.IStreamInputSource;
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
        this.inode.setReadable(true);
        this.inode.setWritable(true);
    }

    @Override
    public Boolean exists() {
        return resource.isAllocated();
    }

    @Override
    public long getLength() {
        if (resource.isAllocated())
            return resource.getLength();
        else
            return 0;
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

    /**
     * Use the written buffer as the input source.
     */
    @Override
    public IStreamInputSource getInputSource(Charset charset) {
        throw new UnsupportedOperationException("Write-Only");
    }

}
