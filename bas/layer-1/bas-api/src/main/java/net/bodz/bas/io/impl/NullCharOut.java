package net.bodz.bas.io.impl;

import java.io.IOException;
import java.nio.CharBuffer;

import net.bodz.bas.io.ICharOut;

public class NullCharOut
        implements ICharOut {

    @Override
    public void write(int ch)
            throws IOException {
    }

    @Override
    public void write(char[] chars)
            throws IOException {
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
    }

    @Override
    public void write(CharBuffer charBuffer)
            throws IOException {
    }

    @Override
    public void write(String s)
            throws IOException {
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
    }

    @Override
    public void write(CharSequence chars, int off, int len)
            throws IOException {
    }

    @Override
    public void flush(boolean strict)
            throws IOException {
    }

    @Override
    public void flush()
            throws IOException {
    }

    @Override
    public void close()
            throws IOException {
    }

    @Override
    public boolean isClosed() {
        return false;
    }

}