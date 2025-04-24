package net.bodz.bas.io;

import java.io.OutputStream;
import java.nio.CharBuffer;

import net.bodz.bas.meta.decl.NotNull;

public class BCharOut
        extends OutputStream
        implements IPrintOut,
                   CharSequence {

    /**
     * @TODO set final in future.
     */
    protected StringBuilder buffer;

    public BCharOut(StringBuilder buffer) {
        if (buffer == null)
            throw new NullPointerException("buffer");
        this.buffer = buffer;
    }

    public BCharOut() {
        this(new StringBuilder());
    }

    public BCharOut(int capacity) {
        this(new StringBuilder(capacity));
    }

    public StringBuilder getBuffer() {
        return buffer;
    }

    public String clear() {
        String s = buffer.toString();
        buffer.setLength(0);
        return s;
    }

    /**
     * Access the {@link #buffer} field instead.
     */
    @Deprecated
    public void setBuffer(StringBuilder buffer) {
        this.buffer = buffer;
    }

    @Override
    public void write(int c) {
        buffer.append((char) c);
    }

    @Override
    public void write(@NotNull char[] chars, int off, int len) {
        buffer.append(chars, off, len);
    }

    @Override
    public void write(@NotNull CharSequence chars, int start, int end) {
        buffer.append(chars, start, end - start);
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }

    @NotNull
    @Override
    public String toString() {
        return buffer.toString();
    }

    /**
     * ⇱ Implementaton Of {@link CharSequence}.
     */
    /* _____________________________ */static section.iface __CHARSEQ__;

    @Override
    public int length() {
        return buffer.length();
    }

    @Override
    public char charAt(int index) {
        return buffer.charAt(index);
    }

    @NotNull
    @Override
    public CharSequence subSequence(int start, int end) {
        return buffer.subSequence(start, end);
    }

    /**
     * ⇱ Implementaton Of {@link Appendable}.
     */
    /* _____________________________ */static section.iface __APPEND__;

    @Override
    public BCharOut append(char c) {
        buffer.append(c);
        return this;
    }

    @Override
    public BCharOut append(String s) {
        buffer.append(s);
        return this;
    }

    @Override
    public BCharOut append(CharSequence csq) {
        buffer.append(csq);
        return this;
    }

    @Override
    public BCharOut append(CharSequence csq, int start, int end) {
        buffer.append(csq, start, end);
        return this;
    }

    // No Exception optimize.

    @Override
    public void write(@NotNull char[] chars) {
        buffer.append(chars);
    }

    @Override
    public void write(@NotNull String s) {
        buffer.append(s);
    }

    @Override
    public void write(@NotNull String string, int off, int len) {
        buffer.append(string, off, len);
    }

    @Override
    public void write(@NotNull CharBuffer buf) {
        buffer.append(buf);
    }

}
