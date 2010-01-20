package net.bodz.bas.sio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public abstract class AbstractByteOut
        implements IByteOutLE, IByteOutBE {

    @Override
    public void write(int b)
            throws IOException {
        byte[] buf = new byte[1];
        write(buf, 0, 1);
    }

    @Override
    public void write(byte[] buf)
            throws IOException {
        write(buf, 0, buf.length);
    }

    @Override
    public void write(ByteBuffer buffer)
            throws IOException {
        byte[] array = buffer.array();
        int off = buffer.arrayOffset();
        int len = buffer.limit();
        write(array, off, len);
    }

    @Override
    public void flush(boolean strict)
            throws IOException {
    }

    @Override
    public void flush()
            throws SIOException {
        try {
            flush(false);
        } catch (IOException e) {
            throw new SIOException();
        }
    }

    @Override
    public void write(boolean b)
            throws IOException {
        write(b ? (byte) 1 : (byte) 0);
    }

    @Override
    public void writeLE(short s)
            throws IOException {
        write(s);
        s >>= 8;
        write(s);
    }

    @Override
    public void writeBE(short s)
            throws IOException {
        write(s >> 8);
        write(s & 0xFF);
    }

    @Override
    public void writeLE(int n)
            throws IOException {
        write(n);
        n >>= 8;
        write(n);
        n >>= 8;
        write(n);
        n >>= 8;
        write(n);
    }

    @Override
    public void writeBE(int n)
            throws IOException {
        write(n >> 24);
        write(n >> 16);
        write(n >> 8);
        write(n);
    }

    @Override
    public void writeLE(long l)
            throws IOException {
        writeLE((int) l);
        l >>= 32;
        writeLE((int) l);
    }

    @Override
    public void writeBE(long l)
            throws IOException {
        writeBE((int) (l >> 32));
        writeBE((int) l);
    }

    @Override
    public synchronized void writeUtf16LE(char c)
            throws IOException {
        write(c);
        c >>= 8;
        write(c);
    }

    @Override
    public synchronized void writeUtf16BE(char c)
            throws IOException {
        write(c >> 8);
        write(c);
    }

    @Override
    public void writeUtf16LE(char[] str)
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

    @Override
    public void writeUtf16BE(char[] str)
            throws IOException {
        if (str == null)
            throw new NullPointerException("str");
        int cb = str.length * 2;
        byte[] buf = new byte[cb];
        int j = 0;
        for (int i = 0; i < str.length; i++) {
            char c = str[i];
            buf[j++] = (byte) (c >> 8);
            buf[j++] = (byte) c;
        }
        write(buf, 0, cb);
    }

    @Override
    public void writeIeee754LE(float f)
            throws IOException {
        writeLE(Float.floatToIntBits(f));
    }

    @Override
    public void writeIeee754LE(double d)
            throws IOException {
        writeLE(Double.doubleToLongBits(d));
    }

    @Override
    public void writeIeee754BE(float f)
            throws IOException {
        writeBE(Float.floatToIntBits(f));
    }

    @Override
    public void writeIeee754BE(double d)
            throws IOException {
        writeBE(Double.doubleToLongBits(d));
    }

    class OutputStreamAdapter
            extends OutputStream {

        @Override
        public void write(int b)
                throws IOException {
            AbstractByteOut.this.write(b);
        }

        @Override
        public void write(byte[] b)
                throws IOException {
            AbstractByteOut.this.write(b);
        }

        @Override
        public void write(byte[] b, int off, int len)
                throws IOException {
            AbstractByteOut.this.write(b, off, len);
        }

        @Override
        public void flush()
                throws IOException {
            AbstractByteOut.this.flush();
        }

    }

    public OutputStream toOutputStream() {
        return new OutputStreamAdapter();
    }

}
