package net.bodz.bas.io;

import java.io.IOException;

public class BufCharOut
        extends AbstractPrintOut
        implements CharSequence, Appendable {

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
