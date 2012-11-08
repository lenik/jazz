package net.bodz.bas.vfs.impl.pseudo;

import java.nio.charset.Charset;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.builtin.CharsResource;

public class CharsFile
        extends PseudoFile {

    private static final long serialVersionUID = 1L;

    private CharsResource resource;

    public CharsFile() {
        this("(no name)", new CharsResource());
    }

    public CharsFile(String name, CharsResource resource) {
        super(name, resource);
        this.resource = resource;
    }

    @Override
    public CharsFile clone() {
        CharsFile o = new CharsFile(getName(), resource);
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

    @Override
    public IStreamInputSource getInputSource(Charset charset) {
        throw new UnsupportedOperationException("Write-Only");
    }

}
