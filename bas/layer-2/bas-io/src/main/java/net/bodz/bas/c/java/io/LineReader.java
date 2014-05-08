package net.bodz.bas.c.java.io;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import net.bodz.bas.err.IteratorTargetException;
import net.bodz.bas.t.iterator.PrefetchedIterator;

/**
 * Line includes EOL characters.
 */
public class LineReader
        extends Reader
        implements ILineReader {

    private final Reader reader;
    private StringBuilder buf;
    private String markedBuf;

    public LineReader(Reader reader) {
        assert reader != null;
        this.reader = reader;
        this.buf = new StringBuilder();
    }

    public Reader getReader() {
        return reader;
    }

    /**
     * @return <code>null</code> if EOF.
     */
    @Override
    public String readLine()
            throws IOException {
        return readLine(Integer.MAX_VALUE);
    }

    @Override
    public Iterator<String> iterator() {
        return new PrefetchedIterator<String>() {

            @Override
            protected String fetch() {
                try {
                    String line = readLine();
                    if (line != null)
                        return line;
                } catch (IOException e) {
                    throw new IteratorTargetException(e.getMessage(), e);
                }
                return end();
            }

        };
    }

    /**
     * @param maxLineLength
     *            length includes line term chars.
     * @return line text includes line term chars, <code>null</code> if EOF is reached.
     * @throws IOException
     */
    public synchronized String readLine(int maxLineLength)
            throws IOException {
        if (maxLineLength < 1)
            throw new IllegalArgumentException("maxLineLength=" + maxLineLength);
        int len = buf.length();
        int reject = 0;
        while (len < maxLineLength) {
            int c = reader.read();
            if (c == -1)
                break;
            buf.append((char) c);
            len++;
            if (c == '\r' && len < maxLineLength) {
                c = reader.read();
                if (c == -1)
                    break;
                buf.append((char) c);
                if (c != '\n')
                    reject = 1;
                else
                    len++;
                break;
            }
            if (isLineTerm(c))
                break;
        }
        if (len == 0)
            return null;
        String line;
        if (reject == 0) { // or: if len == buf.length()
            line = buf.toString();
            buf.setLength(0);
        } else {
            line = buf.substring(0, len);
            buf.delete(0, len);
        }
        return line;
    }

    protected boolean isLineTerm(int c) {
        switch (c) {
        case '\n':
        case '\r':
            return true;
        }
        return false;
    }

    @Override
    public void close()
            throws IOException {
        reader.close();
    }

    @Override
    public int read()
            throws IOException {
        if (buf.length() != 0) {
            int c = buf.charAt(0);
            buf.delete(0, 1);
            return c;
        }
        return reader.read();
    }

    @Override
    public int read(char[] cbuf, int off, int len)
            throws IOException {
        if (buf.length() != 0) {
            int cc = Math.min(buf.length(), len);
            for (int i = 0; i < cc; i++)
                cbuf[off++] = buf.charAt(i);
            buf.delete(0, cc);
            len -= cc;
        }
        return reader.read(cbuf, off, len);
    }

    @Override
    public boolean ready()
            throws IOException {
        return buf.length() > 0 || reader.ready();
    }

    @Override
    public long skip(long n)
            throws IOException {
        int cc = 0;
        if (buf.length() != 0) {
            cc = (int) Math.min(buf.length(), n);
            buf.delete(0, cc);
            n -= cc;
        }
        return cc + reader.skip(n);
    }

    @Override
    public boolean markSupported() {
        return reader.markSupported();
    }

    @Override
    public void mark(int readAheadLimit)
            throws IOException {
        reader.mark(readAheadLimit);
        markedBuf = buf.length() == 0 ? null : buf.toString();
    }

    @Override
    public void reset()
            throws IOException {
        reader.reset();
        if (markedBuf != null) {
            buf.setLength(0);
            buf.append(markedBuf);
        }
    }

}
