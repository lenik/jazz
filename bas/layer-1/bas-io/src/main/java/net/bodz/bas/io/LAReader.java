package net.bodz.bas.io;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.CharBuffer;

import net.bodz.bas.exceptions.OutOfDomainException;

public class LAReader extends FilterReader implements Lookable {

    // LA(cap)
    protected final int cap;

    /** Look-Ahead Buffer */
    protected char[] lab;

    protected int begin;
    protected int current;
    protected boolean full;

    public LAReader(Reader reader, int cap) {
        super(reader);
        // assert cap > 0;
        this.cap = cap;
        this.lab = new char[cap];
        this.begin = 0;
        this.current = 0;
    }

    public LAReader(Reader reader) {
        this(reader, 1);
    }

    public LAReader(String string, int cap) {
        this(new StringReader(string), cap);
    }

    public LAReader(String string) {
        this(new StringReader(string));
    }

    protected final boolean isLabFull() {
        return full;
    }

    protected final boolean isLabFilled() {
        return begin != current || full;
    }

    protected final boolean isLabEmpty() {
        return begin == current && !full;
    }

    /** size of look-ahead buffer */
    protected final int las() {
        if (full)
            return cap;
        int s = current - begin;
        if (s < 0)
            s += cap;
        return s;
    }

    @Override
    public int read() throws IOException {
        if (isLabFilled()) {
            int c = lab[begin++];
            begin %= cap;
            full = false;
            return c;
        }
        return super.read();
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        if (isLabFilled()) {
            // TODO - final int ARRAYCOPY_VALVE = 32;
            int las = las();
            int cc = Math.min(las, len);
            // TODO - if (cc > ARRAYCOPY_VALVE)
            while (cc-- > 0) {
                char c = lab[begin++];
                begin %= cap;
                cbuf[off++] = c;
            }
            full = false;
            len -= cc;
        }
        return super.read(cbuf, off, len);
    }

    @Override
    public int read(char[] cbuf) throws IOException {
        return read(cbuf, 0, cbuf.length);
    }

    @Override
    public int read(CharBuffer target) throws IOException {
        int len = target.remaining();
        char[] cbuf = new char[len];
        int n = read(cbuf, 0, len);
        if (n > 0)
            target.put(cbuf, 0, n);
        return n;
    }

    @Override
    public boolean ready() throws IOException {
        if (isLabFilled())
            return true;
        return super.ready();
    }

    @Override
    public long skip(long n) throws IOException {
        if (isLabFilled()) {
            int cc = las();
            if (cc > n)
                cc = (int) n;
            n -= cc;
            begin = (begin + cc) % cap;
            full = false;
        }
        return super.skip(n);
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public void mark(int readAheadLimit) throws IOException {
        throw new IOException("Mark isn't supported"); //$NON-NLS-1$
    }

    @Override
    public void reset() throws IOException {
        throw new IOException("Mark isn't supported"); //$NON-NLS-1$
    }

    @Override
    public int getLookMax() {
        return lab.length;
    }

    @Override
    public int getLookedLength() {
        if (begin < current)
            return current - begin;
        if (begin == current && !full)
            return 0; //$NON-NLS-1$
        return cap - begin + current;
    }

    @Override
    public int look() throws IOException {
        if (isLabFilled()) {
            return lab[current];
        }
        int c = super.read();
        if (c == -1)
            return -1;
        lab[begin = 0] = (char) c;
        if (cap == 1) {
            current = 0;
            full = true;
        } else {
            current = 1;
        }
        return c;
    }

    @Override
    public int look(char[] cbuf, int off, int len) throws IOException {
        if (len > cap)
            throw new OutOfDomainException("look-len", len, cap); //$NON-NLS-1$
        // len = Math.min(cap, len);
        int las = las();
        if (las < len && !isLabFull()) {
            int cc = len - las;
            int i = 0;
            while (i++ < cc) {
                int c = super.read();
                if (c == -1)
                    break;
                lab[current++] = (char) c;
                current = current % cap;
                if (current == begin) {
                    full = true;
                    break;
                }
            }
            las += i;
        }
        // TODO - final int ARRAYCOPY_VALVE = 32;
        int cc = Math.min(las, len);
        // TODO - if (cc > ARRAYCOPY_VALVE)
        int i = 0;
        int pos = begin;
        while (i++ < cc) {
            char c = lab[pos++];
            pos %= cap;
            cbuf[off++] = c;
        }
        return cc;
    }

    @Override
    public int look(char[] cbuf) throws IOException {
        return look(cbuf, 0, cbuf.length);
    }

    @Override
    public String look(int length) throws IOException {
        char[] cbuf = new char[length];
        int cc = look(cbuf, 0, length);
        return new String(cbuf, 0, cc);
    }

    /**
     * Get compacted look-ahead buffer
     * 
     * @return available content in lab
     */
    public String lookFilled() {
        if (begin < current)
            return new String(lab, begin, current - begin);
        if (begin == current && !full)
            return ""; //$NON-NLS-1$
        return new String(lab, begin, cap - begin) + new String(lab, 0, current);
    }

    @Override
    public String toString() {
        return "look-ahead(" + las() + "/" + cap + "): \"" + lookFilled() //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + "\""; //$NON-NLS-1$
    }

}
