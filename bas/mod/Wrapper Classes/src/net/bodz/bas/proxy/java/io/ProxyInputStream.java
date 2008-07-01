package net.bodz.bas.proxy.java.io;

import java.io.IOException;
import java.io.InputStream;

public class ProxyInputStream extends InputStream {

    protected InputStream proxy;

    public ProxyInputStream(InputStream proxy) {
        assert proxy != null;
        this.proxy = proxy;
    }

    @Override
    public int available() throws IOException {
        return proxy.available();
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
    public void mark(int readlimit) {
        proxy.mark(readlimit);
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
    public int read(byte[] b, int off, int len) throws IOException {
        return proxy.read(b, off, len);
    }

    @Override
    public int read(byte[] b) throws IOException {
        return proxy.read(b);
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
