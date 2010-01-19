package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.CharBuffer;

public class CharBufferCharOut
        extends CharOut {

    private final CharBuffer cb;

    public CharBufferCharOut(CharBuffer cb) {
        this.cb = cb;
    }

    @Override
    public void write(int c)
            throws IOException {
        cb.put((char) c);
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        cb.put(chars, off, len);
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
        try {
            cb.put(string, off, len);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void write(CharBuffer charBuffer)
            throws IOException {
        try {
            cb.put(charBuffer);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

}