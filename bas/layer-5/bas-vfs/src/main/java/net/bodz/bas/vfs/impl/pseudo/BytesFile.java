package net.bodz.bas.vfs.impl.pseudo;

import java.nio.charset.Charset;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.builtin.BytesResource;

public class BytesFile
        extends PseudoFile {

    private static final long serialVersionUID = 1L;

    private BytesResource resource;

    public BytesFile() {
        this("(no name)", new BytesResource());
    }

    public BytesFile(String name, BytesResource resource) {
        super(name, resource);
        this.resource = resource;
    }

    @Override
    public BytesFile clone() {
        BytesFile o = new BytesFile(getName(), resource);
        o.populate(this);
        return o;
    }

    @Override
    public Boolean exists() {
        return resource.isAllocated();
    }

    @Override
    public Long getLength() {
        if (resource.isAllocated())
            return (long) resource.getLength();
        else
            return null;
    }

    @Override
    public boolean isReadable() {
        return true;
    }

    @Override
    public boolean isWritable() {
        return true;
    }

    @Override
    public boolean delete() {
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
