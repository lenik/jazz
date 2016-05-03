package net.bodz.bas.io;

import java.nio.CharBuffer;

public class BCharOut
        extends AbstractPrintOut
        implements CharSequence {

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
    public void write(char[] chars, int off, int len) {
        buffer.append(chars, off, len);
    }

    @Override
    public void write(CharSequence chars, int start, int end) {
        buffer.append(chars, start, end);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

    /** ⇱ Implementaton Of {@link CharSequence}. */
    /* _____________________________ */static section.iface __CHARSEQ__;

    @Override
    public int length() {
        return buffer.length();
    }

    @Override
    public char charAt(int index) {
        return buffer.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return buffer.subSequence(start, end);
    }

    /** ⇱ Implementaton Of {@link Appendable}. */
    /* _____________________________ */static section.iface __APPEND__;

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

    @Override
    public BCharOut append(char c) {
        buffer.append(c);
        return this;
    }

    // No Exception optimize.

    @Override
    public void write(char[] chars) {
        buffer.append(chars);
    }

    @Override
    public void write(String s) {
        buffer.append(s);
    }

    @Override
    public void write(String string, int off, int len) {
        buffer.append(string, off, len);
    }

    @Override
    public void write(CharBuffer buf) {
        buffer.append(buf);
    }

    @Override
    public AbstractCharOut append(String s) {
        buffer.append(s);
        return this;
    }

}
