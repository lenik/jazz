package net.bodz.bas.vfs.impl.fake;

import java.nio.charset.Charset;

import net.bodz.bas.io.resource.builtin.StringSource;
import net.bodz.bas.io.resource.preparation.IStreamReadPreparation;
import net.bodz.bas.io.resource.preparation.IStreamWritePreparation;
import net.bodz.bas.io.resource.preparation.StreamReadPreparation;
import net.bodz.bas.util.ObjectInfo;

public class InputStringFile
        extends FakeFile {

    private final String text;

    private Long sizeInBytes;

    public InputStringFile(String text) {
        this(ObjectInfo.getObjectId(text), text);
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
    public IStreamReadPreparation forRead() {
        return new StreamReadPreparation(new StringSource(text));
    }

    @Override
    public IStreamWritePreparation forWrite() {
        throw new UnsupportedOperationException();
    }

}
