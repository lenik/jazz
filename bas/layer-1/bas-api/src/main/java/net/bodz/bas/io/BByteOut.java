package net.bodz.bas.io;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import net.bodz.bas.io.adapter.OutputStreamByteOut;

public class BByteOut
        extends OutputStreamByteOut {

    public BByteOut() {
        super(new ByteArrayOutputStream());
    }

    public BByteOut(int initialSize) {
        super(new ByteArrayOutputStream(initialSize));
    }

    public ByteArrayOutputStream getBuffer() {
        return (ByteArrayOutputStream) out;
    }

    public int size() {
        return getBuffer().size();
    }

    public byte[] toByteArray() {
        return getBuffer().toByteArray();
    }

    public byte[] flip() {
        ByteArrayOutputStream buf = getBuffer();
        byte[] bytes = buf.toByteArray();
        buf.reset();
        return bytes;
    }

    @Override
    public String toString() {
        return getBuffer().toString();
    }

    public String toString(String charset)
            throws UnsupportedEncodingException {
        return getBuffer().toString(charset);
    }

}
