package net.bodz.bas.sio;

import java.io.IOException;
import java.io.Writer;
import java.nio.CharBuffer;

public abstract class AbstractCharOut
        implements ICharOut {

    @Override
    public void write(char[] chars)
            throws IOException {
        write(chars, 0, chars.length);
    }

    @Override
    public void write(CharSequence chars, int off, int len)
            throws IOException {
        CharBuffer charBuffer = CharBuffer.wrap(chars, off, off + len);
        write(charBuffer);
    }

    @Override
    public void write(String s)
            throws IOException {
        write(s.toCharArray());
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
        char[] buf = new char[len];
        string.getChars(off, off + len, buf, 0);
        write(buf, 0, len);
    }

    @Override
    public void write(CharBuffer charBuffer)
            throws IOException {
        if (charBuffer == null)
            throw new NullPointerException("charBuffer");
        char[] array = charBuffer.array();
        int offset = charBuffer.arrayOffset();
        int limit = charBuffer.limit();
        write(array, offset, limit);
    }

    @Override
    public void flush(boolean strict)
            throws IOException {
    }

    @Override
    public void flush()
            throws IOException {
        flush(false);
    }

    @Override
    public void close()
            throws IOException {
        flush(true);
    }

    public Writer toWriter() {
        return new CharOutWriter(this);
    }

}
