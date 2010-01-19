package net.bodz.bas.sio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public abstract class ByteOut
        implements IByteOut {

    @Override
    public void write(ByteBuffer buffer)
            throws IOException {
        byte[] array = buffer.array();
        int off = buffer.arrayOffset();
        int len = buffer.limit();
        write(array, off, len);
    }

    @Override
    public void write(int b)
            throws IOException {
        byte[] buf = new byte[1];
        write(buf, 0, 1);
    }

    @Override
    public void flush(boolean strict)
            throws IOException {
    }

    public void flush()
            throws SIOException {
        try {
            flush(false);
        } catch (IOException e) {
            throw new SIOException();
        }
    }

    public void print(byte[] buf)
            throws SIOException {
        try {
            write(buf, 0, buf.length);
        } catch (IOException e) {
            throw new SIOException(e);
        }
    }

    public void print(byte b)
            throws SIOException {
        try {
            write(b);
        } catch (IOException e) {
            throw new SIOException(e);
        }
    }

    public void printLE(short s)
            throws SIOException {
        try {
            write(s);
            s >>= 8;
            write(s);
        } catch (IOException e) {
            throw new SIOException(e);
        }
    }

    public void printLE(int n)
            throws SIOException {
        try {
            write(n);
            n >>= 8;
            write(n);
            n >>= 8;
            write(n);
            n >>= 8;
            write(n);
        } catch (IOException e) {
            throw new SIOException(e);
        }
    }

    public void printLE(long l)
            throws SIOException {
        printLE((int) l);
        l >>= 32;
        printLE((int) l);
    }

    public void print(boolean b)
            throws SIOException {
        print(b ? (byte) 1 : (byte) 0);
    }

    public synchronized void printUtf16LE(char c)
            throws SIOException {
        try {
            write(c);
            c >>= 8;
            write(c);
        } catch (IOException e) {
            throw new SIOException(e);
        }
    }

    public void printUtf16LE(char[] str)
            throws SIOException {
        if (str == null)
            throw new NullPointerException("str");
        int cb = str.length * 2;
        byte[] buf = new byte[cb];
        int j = 0;
        for (int i = 0; i < str.length; i++) {
            char c = str[i];
            buf[j++] = (byte) (c & 0xFF);
            c >>= 8;
            buf[j++] = (byte) c;
        }
        try {
            write(buf, 0, cb);
        } catch (IOException e) {
            throw new SIOException(e);
        }
    }

    public void printIeee754(double d)
            throws SIOException {
        printLE(Double.doubleToLongBits(d));
    }

    public void printIeee754(float f)
            throws SIOException {
        printLE(Float.floatToIntBits(f));
    }

    class OutputStreamAdapter
            extends OutputStream {

        @Override
        public void write(int b)
                throws IOException {
            ByteOut.this.write(b);
        }

        @Override
        public void write(byte[] b, int off, int len)
                throws IOException {
            ByteOut.this.write(b, off, len);
        }

        @Override
        public void flush()
                throws IOException {
            ByteOut.this.flush();
        }

    }

    public OutputStream toOutputStream() {
        return new OutputStreamAdapter();
    }

    public static final ByteOut nil = NullByteOut.getInstance();
    public static final ByteOut stdout = new OutputStreamByteOut(System.out);
    public static final ByteOut stderr = new OutputStreamByteOut(System.err);

}
