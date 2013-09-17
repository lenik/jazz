package net.bodz.bas.io;

import java.io.IOException;

public class BCharOut
        extends AbstractPrintOut
        implements CharSequence, Appendable {

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

    /**
     * Access the {@link #buffer} field instead.
     */
    @Deprecated
    public void setBuffer(StringBuilder buffer) {
        this.buffer = buffer;
    }

    @Override
    public void write(int c)
            throws IOException {
        buffer.append((char) c);
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        buffer.append(chars, off, len);
    }

    @Override
    public void write(CharSequence chars, int off, int len)
            throws IOException {
        buffer.append(chars, off, off + len);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

    /** ⇱ Implementaton Of {@link CharSequence}. */
    ;

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
    ;

    @Override
    public Appendable append(CharSequence csq)
            throws IOException {
        return buffer.append(csq);
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end)
            throws IOException {
        return buffer.append(csq, start, end);
    }

    @Override
    public Appendable append(char c)
            throws IOException {
        return buffer.append(c);
    }

    public String flip() {
        String s = buffer.toString();
        buffer.setLength(0);
        return s;
    }

}