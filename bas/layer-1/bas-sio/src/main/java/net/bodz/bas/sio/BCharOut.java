package net.bodz.bas.sio;

import java.io.IOException;

public class BCharOut
        extends AbstractPrintCharOut {

    /**
     * @TODO set final in futuer.
     */
    protected StringBuffer buffer;

    public BCharOut(StringBuffer buffer) {
        if (buffer == null)
            throw new NullPointerException("buffer");
        this.buffer = buffer;
    }

    public BCharOut() {
        this(new StringBuffer());
    }

    public BCharOut(int capacity) {
        this(new StringBuffer(capacity));
    }

    public StringBuffer getBuffer() {
        return buffer;
    }

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

    public char charAt(int index) {
        return buffer.charAt(index);
    }

    public int length() {
        return buffer.length();
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

    public String flip() {
        String s = buffer.toString();
        buffer.setLength(0);
        return s;
    }

}
