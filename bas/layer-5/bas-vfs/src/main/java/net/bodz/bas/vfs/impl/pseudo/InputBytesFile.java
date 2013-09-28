package net.bodz.bas.vfs.impl.pseudo;

import java.nio.charset.Charset;

import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.io.res.IStreamOutputTarget;
import net.bodz.bas.io.res.builtin.ByteArrayResource;

public class InputBytesFile
        extends PseudoFile {

    private final byte[] bytes;

    public InputBytesFile(byte[] bytes) {
        this(ObjectInfo.getSimpleId(bytes), bytes);
    }

    public InputBytesFile(String name, byte[] bytes) {
        super(name, new ByteArrayResource(bytes));
        this.bytes = bytes;
        setReadable(true);
        setWritable(false);
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public long getLength() {
        return bytes.length;
    }

    @Override
    protected IStreamOutputTarget _getOutputTarget(Charset charset) {
        throw new ReadOnlyException();
    }

}
