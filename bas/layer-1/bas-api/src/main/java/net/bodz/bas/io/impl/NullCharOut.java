package net.bodz.bas.io.impl;

import java.io.IOException;
import java.io.Writer;
import java.nio.CharBuffer;

import net.bodz.bas.io.ICharOut;

public class NullCharOut
        extends Writer
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
    public void write(CharSequence chars)
            throws IOException {
    }

    @Override
    public void write(CharSequence chars, int start, int end)
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

    @Override
    public Writer toWriter() {
        return this;
    }

    /** ⇱ Implementation Of {@link Writer}. */
    /* _____________________________ */static section.iface __WRITER__;

    @Override
    public Writer append(CharSequence csq)
            throws IOException {
        return this;
    }

    @Override
    public Writer append(CharSequence csq, int start, int end)
            throws IOException {
        return this;
    }

    @Override
    public Writer append(char c)
            throws IOException {
        return this;
    }

}