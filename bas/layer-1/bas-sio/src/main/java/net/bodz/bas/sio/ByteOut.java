package net.bodz.bas.sio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
    public void flush()
            throws IOException {
    }

    public void print(byte[] buf)
            throws IOException {
        write(buf, 0, buf.length);
    }

    public void print(byte b)
            throws IOException {
        write(b);
    }

    public void printLE(short s)
            throws IOException {
        write(s);
        s >>= 8;
        write(s);
    }

    public void printLE(int n)
            throws IOException {
        write(n);
        n >>= 8;
        write(n);
        n >>= 8;
        write(n);
        n >>= 8;
        write(n);
    }

    public void printLE(long l)
            throws IOException {
        printLE((int) l);
        l >>= 32;
        printLE((int) l);
    }

    public void print(boolean b)
            throws IOException {
        print(b ? (byte) 1 : (byte) 0);
    }

    public synchronized void printUtf16LE(char c)
            throws IOException {
        write(c);
        c >>= 8;
        write(c);
    }

    public void printUtf16LE(char[] str)
            throws IOException {
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
        write(buf, 0, cb);
    }

    public void printIeee754(double d)
            throws IOException {
        printLE(Double.doubleToLongBits(d));
    }

    public void printIeee754(float f)
            throws IOException {
        printLE(Float.floatToIntBits(f));
    }

    public void printSerialization(Object obj)
            throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ObjectOutputStream out;
        out = new ObjectOutputStream(buf);
        out.writeObject(obj);

        print(buf.toByteArray());
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
