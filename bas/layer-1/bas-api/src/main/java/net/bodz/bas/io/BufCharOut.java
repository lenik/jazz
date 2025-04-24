package net.bodz.bas.io;

import java.io.IOException;
import java.io.Writer;

import net.bodz.bas.meta.decl.NotNull;

public class BufCharOut
        extends Writer
        implements IPrintOut,
                   CharSequence,
                   Appendable {

    /**
     * @TODO set final in future.
     */
    protected StringBuffer buffer;

    public BufCharOut(StringBuffer buffer) {
        if (buffer == null)
            throw new NullPointerException("buffer");
        this.buffer = buffer;
    }

    public BufCharOut() {
        this(new StringBuffer());
    }

    public BufCharOut(int capacity) {
        this(new StringBuffer(capacity));
    }

    public StringBuffer getBuffer() {
        return buffer;
    }

    /**
     * Access the {@link #buffer} field instead.
     */
    @Deprecated
    public void setBuffer(StringBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void write(int c)
            throws IOException {
        buffer.append((char) c);
    }

    @Override
    public void write(@NotNull char[] chars, int off, int len)
            throws IOException {
        buffer.append(chars, off, len);
    }

    @Override
    public void write(@NotNull CharSequence chars, int start, int end)
            throws IOException {
        buffer.append(chars, start, end);
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
    public BufCharOut append(CharSequence csq)
            throws IOException {
        buffer.append(csq);
        return this;
    }

    @Override
    public BufCharOut append(CharSequence csq, int start, int end)
            throws IOException {
        buffer.append(csq, start, end);
        return this;
    }

    @Override
    public BufCharOut append(char c)
            throws IOException {
        buffer.append(c);
        return this;
    }

    public String flip() {
        String s = buffer.toString();
        buffer.setLength(0);
        return s;
    }

}
