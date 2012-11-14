package net.bodz.bas.vfs.impl.pseudo;

import java.nio.charset.Charset;

import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.builtin.ByteArrayResource;

public class InputBytesFile
        extends PseudoFile {

    private final byte[] bytes;

    public InputBytesFile(byte[] bytes) {
        this(ObjectInfo.getSimpleId(bytes), bytes);
    }

    public InputBytesFile(String name, byte[] bytes) {
        super(name, new ByteArrayResource(bytes));
        this.bytes = bytes;
    }

    @Override
    public InputBytesFile clone() {
        InputBytesFile o = new InputBytesFile(getName(), bytes);
        o.populate(this);
        return o;
    }

    @Override
    public boolean isReadable() {
        return true;
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public Long getLength() {
        return Long.valueOf(bytes.length);
    }

    @Override
    public IStreamOutputTarget getOutputTarget(boolean append, Charset charset) {
        throw new ReadOnlyException();
    }

}
