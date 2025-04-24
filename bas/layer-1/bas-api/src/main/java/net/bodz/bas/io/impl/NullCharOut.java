package net.bodz.bas.io.impl;

import java.io.Writer;
import java.nio.CharBuffer;

import net.bodz.bas.io.ICharOut;
import net.bodz.bas.meta.decl.NotNull;

public class NullCharOut
        extends Writer
        implements ICharOut {

    @Override
    public void write(int ch) {
    }

    @Override
    public void write(@NotNull char[] chars) {
    }

    @Override
    public void write(@NotNull char[] chars, int off, int len) {
    }

    @Override
    public void write(@NotNull CharBuffer charBuffer) {
    }

    @Override
    public void write(@NotNull String s) {
    }

    @Override
    public void write(@NotNull String string, int off, int len) {
    }

    @Override
    public void write(@NotNull CharSequence chars) {
    }

    @Override
    public void write(@NotNull CharSequence chars, int start, int end) {
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }

    @Override
    public Writer toWriter() {
        return this;
    }

    /**
     * ⇱ Implementation Of {@link Writer}.
     */
    /* _____________________________ */static section.iface __WRITER__;

    @Override
    public Writer append(CharSequence csq) {
        return this;
    }

    @Override
    public Writer append(CharSequence csq, int start, int end) {
        return this;
    }

    @Override
    public Writer append(char c) {
        return this;
    }

}