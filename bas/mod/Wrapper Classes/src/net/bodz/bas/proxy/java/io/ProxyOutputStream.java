package net.bodz.bas.proxy.java.io;

import java.io.IOException;
import java.io.OutputStream;

public class ProxyOutputStream extends OutputStream {

    protected OutputStream proxy;

    public ProxyOutputStream(OutputStream proxy) {
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
    public void flush() throws IOException {
        proxy.flush();
    }

    @Override
    public int hashCode() {
        return proxy.hashCode();
    }

    @Override
    public String toString() {
        return proxy.toString();
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        proxy.write(b, off, len);
    }

    @Override
    public void write(byte[] b) throws IOException {
        proxy.write(b);
    }

    @Override
    public void write(int b) throws IOException {
        proxy.write(b);
    }

}
