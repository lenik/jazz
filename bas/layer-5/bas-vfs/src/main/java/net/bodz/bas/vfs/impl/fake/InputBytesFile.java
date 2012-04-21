package net.bodz.bas.vfs.impl.fake;

import java.nio.charset.Charset;

import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.builtin.ByteArrayResource;
import net.bodz.bas.util.ObjectInfo;

public class InputBytesFile
        extends FakeFile {

    private final byte[] bytes;

    public InputBytesFile(byte[] bytes) {
        this(ObjectInfo.getObjectId(bytes), bytes);
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
    public IStreamInputSource toSource() {
        return new ByteArrayResource(bytes);
    }

    @Override
    public IStreamOutputTarget toTarget() {
        // return new ByteArrayResource(bytes);
        throw new ReadOnlyException();
    }

}
