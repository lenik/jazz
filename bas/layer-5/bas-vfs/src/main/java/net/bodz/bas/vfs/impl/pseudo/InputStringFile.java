package net.bodz.bas.vfs.impl.pseudo;

import java.nio.charset.Charset;

import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.builtin.CharArrayResource;

public class InputStringFile
        extends PseudoFile {

    private final char[] charArray;
    private transient String text;

    private Long encodedSize;

    public InputStringFile(String text) {
        this(ObjectInfo.getSimpleId(text), text);
    }

    public InputStringFile(String name, String text) {
        this(name, text.toCharArray());
        this.text = text;
    }

    public InputStringFile(String name, char[] charArray) {
        super(name, new CharArrayResource(charArray));
        this.charArray = charArray;
    }

    @Override
    public InputStringFile clone() {
        InputStringFile o = new InputStringFile(getName(), charArray);
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

    public char[] getCharArray() {
        return charArray;
    }

    public String getText() {
        if (text == null)
            text = new String(charArray);
        return text;
    }

    @Override
    public void setPreferredCharset(Charset charset) {
        Charset oldCharset = getPreferredCharset();
        if (!oldCharset.equals(charset)) {
            super.setPreferredCharset(charset);
            encodedSize = null;
        }
    }

    @Override
    public Long getLength() {
        if (encodedSize == null) {
            byte[] bytes = getText().getBytes(getPreferredCharset());
            encodedSize = Long.valueOf(bytes.length);
        }
        return encodedSize;
    }

    @Override
    public IStreamOutputTarget getOutputTarget(boolean appendMode, Charset charset) {
        throw new UnsupportedOperationException("Read-Only");
    }

}
