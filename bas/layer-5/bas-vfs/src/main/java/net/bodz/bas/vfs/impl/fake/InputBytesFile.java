package net.bodz.bas.vfs.impl.fake;

import java.nio.charset.Charset;

import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.io.resource.builtin.ByteArrayResource;

public class InputBytesFile
        extends FakeFile {

    private final byte[] bytes;

    public InputBytesFile(byte[] bytes) {
        this(ObjectInfo.getSimpleId(bytes), bytes);
    }

    public InputBytesFile(String name, byte[] bytes) {
        super(name);
        if (bytes == null)
            throw new NullPointerException("bytes");
        this.bytes = bytes;
    }

    @Override
    public InputBytesFile clone() {
        InputBytesFile o = new InputBytesFile(pathString, bytes);
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
    public IStreamResource getResource(Charset charset) {
        throw new UnsupportedOperationException("Read-only resource.");
    }

    @Override
    public IStreamInputSource getInputSource(Charset charset) {
        ByteArrayResource resource = new ByteArrayResource(bytes);
        resource.setCharset(charset);
        return resource;
    }

    @Override
    public IStreamOutputTarget getOutputTarget(boolean append, Charset charset) {
        // return new ByteArrayResource(bytes);
        throw new ReadOnlyException();
    }

}
