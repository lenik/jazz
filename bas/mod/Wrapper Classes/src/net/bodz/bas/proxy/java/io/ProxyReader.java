package net.bodz.bas.proxy.java.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

/**
 * @see java.io.FilterReader
 */
@Deprecated
public class ProxyReader extends Reader {

    protected Reader proxy;

    public ProxyReader(Reader proxy) {
        assert proxy != null;
        this.proxy = proxy;
    }

    @Override
    public void close() throws IOException {
        proxy.close();
    }

    @Override
    public boolean equals(Object obj) {
        return proxy.equals(obj);
    }

    @Override
    public int hashCode() {
        return proxy.hashCode();
    }

    @Override
    public void mark(int readAheadLimit) throws IOException {
        proxy.mark(readAheadLimit);
    }

    @Override
    public boolean markSupported() {
        return proxy.markSupported();
    }

    @Override
    public int read() throws IOException {
        return proxy.read();
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return proxy.read(cbuf, off, len);
    }

    @Override
    public int read(char[] cbuf) throws IOException {
        return proxy.read(cbuf);
    }

    @Override
    public int read(CharBuffer target) throws IOException {
        return proxy.read(target);
    }

    @Override
    public boolean ready() throws IOException {
        return proxy.ready();
    }

    @Override
    public void reset() throws IOException {
        proxy.reset();
    }

    @Override
    public long skip(long n) throws IOException {
        return proxy.skip(n);
    }

    @Override
    public String toString() {
        return proxy.toString();
    }

}
