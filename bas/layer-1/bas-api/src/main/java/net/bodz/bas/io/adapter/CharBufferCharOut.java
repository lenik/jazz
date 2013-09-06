package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.CharBuffer;

import net.bodz.bas.io.AbstractCharOut;

public class CharBufferCharOut
        extends AbstractCharOut {

    private final CharBuffer charBuffer;

    public CharBufferCharOut(CharBuffer charBuffer) {
        if (charBuffer == null)
            throw new NullPointerException("charBuffer");
        this.charBuffer = charBuffer;
    }

    @Override
    public void write(int c)
            throws IOException {
        charBuffer.put((char) c);
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        charBuffer.put(chars, off, len);
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
        try {
            charBuffer.put(string, off, len);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void write(CharBuffer charBuffer)
            throws IOException {
        try {
            charBuffer.put(charBuffer);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    @Override
    public int hashCode() {
        return 0x56ec273b + charBuffer.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CharBufferCharOut))
            return false;
        CharBufferCharOut o = (CharBufferCharOut) obj;
        return charBuffer.equals(o.charBuffer);
    }

    @Override
    public String toString() {
        return charBuffer.toString();
    }

}