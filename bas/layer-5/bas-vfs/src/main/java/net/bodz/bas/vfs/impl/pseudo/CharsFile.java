package net.bodz.bas.vfs.impl.pseudo;

import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.builtin.CharsResource;

public class CharsFile
        extends PseudoFile {

    private CharsResource resource;

    public CharsFile() {
        this("(no name)", new CharsResource());
    }

    public CharsFile(String name, CharsResource resource) {
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
    public Long getLength() {
        if (resource.isAllocated())
            return (long) resource.getLength();
        else
            return null;
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

    @Override
    public IStreamInputSource getInputSource(Charset charset) {
        throw new UnsupportedOperationException("Write-Only");
    }

}
