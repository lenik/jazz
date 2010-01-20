package net.bodz.bas.sio;

import java.io.IOException;

public class StringCharIn
        extends AbstractCharIn {

    private final String string;
    private final int start;
    private final int end;

    private int position;

    /**
     * @throws NullPointerException
     *             If <code>string</code> is <code>null</code>.
     */
    public StringCharIn(String string) {
        if (string == null)
            throw new NullPointerException("string");
        this.string = string;
        position = start = 0;
        end = string.length();
    }

    /**
     * @throws NullPointerException
     *             If <code>string</code> is <code>null</code>.
     * @throws IndexOutOfBoundsException
     *             If <code>start</code> or <code>end</code> is out of bound.
     */
    public StringCharIn(String string, int start, int end) {
        if (string == null)
            throw new NullPointerException("string");
        if (start < 0 || start >= string.length())
            throw new IndexOutOfBoundsException("bad start index: " + start);
        if (end < start || end > string.length())
            throw new IndexOutOfBoundsException("bad end index: " + end);
        this.string = string;
        this.start = start;
        this.end = end;
        this.position = start;
    }

    @Override
    public int read()
            throws IOException {
        if (position < end)
            return string.charAt(position++);
        return -1;
    }

    @Override
    public int read(char[] chars, int off, int len)
            throws IOException {
        int cbRead = Math.min(len, end - position);
        if (cbRead <= 0)
            return -1;
        string.getChars(position, position + cbRead, chars, off);
        position += cbRead;
        return cbRead;
    }

    @Override
    public int hashCode() {
        int hash = 0xeeecb441;
        hash += string.hashCode();
        hash += start * 13;
        hash += end * 17;
        hash += position * 253;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StringCharIn))
            return false;
        StringCharIn o = (StringCharIn) obj;
        if (start != o.start || end != o.end || position != o.position)
            return false;
        if (string.equals(o.string))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String graph = string.substring(start, position) + "|" + string.substring(position, end);
        return graph;
    }

}
