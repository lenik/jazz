package net.bodz.bas.io.process;

import java.io.IOException;
import java.io.OutputStream;

public class DbgOut
        extends OutputStream {

    OutputStream _orig;
    String hint;

    public DbgOut(OutputStream _orig, String hint) {
        this._orig = _orig;
        this.hint = hint;
    }

    public OutputStream getWrapped() {
        return _orig;
    }

    @Override
    public void write(int b)
            throws IOException {
        // X>'a\n
        String str = hint + ">'" + (char) b + "\n";
        getWrapped().write(str.getBytes());
    }

    @Override
    public void write(byte[] b)
            throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len)
            throws IOException {
        // X>"abc\n
        String str = new String(b, off, len);
        str = str.replace("\t", "\\t");
        str = str.replace("\n", "\\n");
        str = str.replace("\r", "\\r");
        str = hint + ">\"" + str + "\n";
        getWrapped().write(str.getBytes());
    }

    @Override
    public void flush()
            throws IOException {
        getWrapped().write("\\flush".getBytes());
        getWrapped().flush();
    }

    @Override
    public void close()
            throws IOException {
        getWrapped().write("\\close".getBytes());
        getWrapped().close();
    }

    @Override
    public boolean equals(Object obj) {
        return getWrapped().equals(obj);
    }

    @Override
    public int hashCode() {
        return getWrapped().hashCode();
    }

    @Override
    public String toString() {
        return getWrapped().toString();
    }

}
