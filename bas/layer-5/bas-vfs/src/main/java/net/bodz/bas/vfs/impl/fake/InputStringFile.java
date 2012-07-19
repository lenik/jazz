package net.bodz.bas.vfs.impl.fake;

import java.nio.charset.Charset;

import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.io.resource.builtin.StringSource;

public class InputStringFile
        extends FakeFile {

    private final String text;

    private Long sizeInBytes;

    public InputStringFile(String text) {
        this(ObjectInfo.getSimpleId(text), text);
    }

    public InputStringFile(String name, String text) {
        super(name);
        if (text == null)
            throw new NullPointerException("text");
        this.text = text;
    }

    @Override
    public InputStringFile clone() {
        InputStringFile o = new InputStringFile(getName(), text);
        o.populate(this);
        return o;
    }

    @Override
    public boolean isReadable() {
        return true;
    }

    @Override
    public boolean isWritable() {
        return false;
    }

    public String getText() {
        return text;
    }

    @Override
    public void setPreferredCharset(Charset charset) {
        Charset oldCharset = getPreferredCharset();
        if (!oldCharset.equals(charset)) {
            super.setPreferredCharset(charset);
            sizeInBytes = null;
        }
    }

    @Override
    public Long getLength() {
        if (sizeInBytes == null) {
            byte[] bytes = text.getBytes(getPreferredCharset());
            sizeInBytes = Long.valueOf(bytes.length);
        }
        return sizeInBytes;
    }

    @Override
    public IStreamInputSource getInputSource(Charset charset) {
        return new StringSource(text);
    }

    @Override
    public IStreamOutputTarget getOutputTarget(boolean appendMode, Charset charset) {
        throw new UnsupportedOperationException("Read-Only");
    }

    @Override
    public IStreamResource getResource(Charset charset) {
        throw new UnsupportedOperationException("Read-Only");
    }
}
