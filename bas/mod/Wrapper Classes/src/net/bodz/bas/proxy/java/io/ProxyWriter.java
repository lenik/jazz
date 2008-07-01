package net.bodz.bas.proxy.java.io;

import java.io.IOException;
import java.io.Writer;

public class ProxyWriter extends Writer {

    protected Writer proxy;

    public ProxyWriter(Writer proxy) {
        assert proxy != null;
        this.proxy = proxy;
    }

    @Override
    public Writer append(char c) throws IOException {
        return proxy.append(c);
    }

    @Override
    public Writer append(CharSequence csq, int start, int end)
            throws IOException {
        return proxy.append(csq, start, end);
    }

    @Override
    public Writer append(CharSequence csq) throws IOException {
        return proxy.append(csq);
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
    public void write(char[] cbuf, int off, int len) throws IOException {
        proxy.write(cbuf, off, len);
    }

    @Override
    public void write(char[] cbuf) throws IOException {
        proxy.write(cbuf);
    }

    @Override
    public void write(int c) throws IOException {
        proxy.write(c);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        proxy.write(str, off, len);
    }

    @Override
    public void write(String str) throws IOException {
        proxy.write(str);
    }

}
