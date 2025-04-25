package net.bodz.bas.io.impl;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.io.ILookCharsAhead;

public class LAReader
        extends FilterReader
        implements ILookCharsAhead {

    // LA(cap)
    final int capacity;

    /**
     * look-ahead ring buffer
     */
    char[] ringbuf;

    int start;
    int end;
    //    protected boolean full;
    int length;

    public LAReader(Reader reader, int capacity) {
        super(reader);
        // assert cap > 0;
        this.capacity = capacity;
        this.ringbuf = new char[capacity];
        this.start = 0;
        this.end = 0;
    }

    public LAReader(Reader reader) {
        this(reader, 1);
    }

    public LAReader(String string, int capacity) {
        this(new StringReader(string), capacity);
    }

    public LAReader(String string) {
        this(new StringReader(string));
    }

    protected final boolean hasNoRemaining() {
        return length == capacity;
    }

    protected final boolean isNotEmpty() {
        return length != 0;
    }

    protected final boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int read()
            throws IOException {
        if (isNotEmpty()) {
            int c = ringbuf[start++];
            start %= capacity;
            length--;
            return c;
        }
        return super.read();
    }

    @Override
    public int read(char[] cbuf, int off, int len)
            throws IOException {
        if (isNotEmpty()) {
            // TODO - final int ARRAYCOPY_VALVE = 32;
            int n = Math.min(length, len);
            for (int i = 0; i < n; i++) {
                char c = ringbuf[start++];
                start %= capacity;
                cbuf[off++] = c;
            }
            length -= n;
            len -= n;
        }
        return super.read(cbuf, off, len);
    }

    @Override
    public boolean ready()
            throws IOException {
        if (isNotEmpty())
            return true;
        return super.ready();
    }

    @Override
    public long skip(long numChars)
            throws IOException {
        if (isNotEmpty()) {
            int n = (int) Math.min(numChars, length);
            numChars -= n;
            start = (start + n) % capacity;
            length -= n;
        }
        return super.skip(numChars);
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public void mark(int readAheadLimit)
            throws IOException {
        throw new IOException("Mark isn't supported");
    }

    @Override
    public void reset()
            throws IOException {
        throw new IOException("Mark isn't supported");
    }

    @Override
    public int getLookCapacity() {
        return ringbuf.length;
    }

    @Override
    public int getLookLimit() {
        return length;
    }

    @Override
    public int lookChar()
            throws IOException {
        if (isNotEmpty()) {
            return ringbuf[start];
        }
        int ch = super.read();
        if (ch == -1)
            return -1;
        ringbuf[start = 0] = (char) ch;
        end = 1 % capacity;
        length = 1;
        return ch;
    }

    @Override
    public int look(char[] cbuf, int off, int len)
            throws IOException {
        if (len > capacity)
            throw new OutOfDomainException("look-len", len, capacity);
        if (len > length) { // fill more
            int n = len - length;
            int i = 0;
            while (i++ < n) {
                int c = super.read();
                if (c == -1)
                    break;
                ringbuf[end++] = (char) c;
                end = end % capacity;
                if (++length >= capacity)
                    break;
            }
        }
        int n = Math.min(length, len);
        int pos = this.start;
        for (int i = 0; i < n; i++) {
            char c = ringbuf[pos++];
            pos %= capacity;
            cbuf[off++] = c;
        }
        return n;
    }

    /**
     * Get compacted look-ahead buffer
     *
     * @return filled part of the buffer
     */
    public String lookBuffer() {
        if (length == 0)
            return "";
        if (start < end)
            return new String(ringbuf, start, end - start);
        else
            return new String(ringbuf, start, capacity - start) + new String(ringbuf, 0, end);
    }

    @Override
    public String toString() {
        return "look-ahead(" + length + "/" + capacity + "): \"" + lookBuffer() + "\"";
    }

}
