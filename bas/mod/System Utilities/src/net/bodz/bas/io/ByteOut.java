package net.bodz.bas.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public abstract class ByteOut implements IByteOut {

    private byte[] buf = new byte[32];

    public void _write(byte[] buf, int off, int len) {
        try {
            write(buf, off, len);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void _write(byte b) {
        byte[] bv = new byte[1];
        bv[0] = b;
        _write(bv, 0, 1);
    }

    public void print(byte[] buf) {
        _write(buf, 0, buf.length);
    }

    public synchronized void print(byte b) {
        _write(b);
    }

    public synchronized void print(short s) {
        buf[0] = (byte) (s & 0xFF);
        buf[1] = (byte) (s >> 8);
        _write(buf, 0, 2);
    }

    public synchronized void print(int i) {
        for (int x = 0; x < 4; x++) {
            buf[x] = (byte) (i & 0xFF);
            i >>= 8;
        }
        _write(buf, 0, 4);
    }

    public synchronized void print(long l) {
        for (int x = 0; x < 8; x++) {
            buf[x] = (byte) (l & 0xFF);
            l >>= 8;
        }
        _write(buf, 0, 4);
    }

    public void print(boolean b) {
        print(b ? (byte) 1 : (byte) 0);
    }

    public synchronized void print(char c) {
        for (int x = 0; x < 2; x++) {
            buf[x] = (byte) (c & 0xFF);
            c >>= 8;
        }
        _write(buf, 0, 4);
    }

    public void print(char[] s) {
        assert s != null;
        int len = s.length * 2;
        if (len < buf.length) {
            synchronized (this) {
                _print(s, buf);
            }
        } else {
            byte[] buf = new byte[len];
            _print(s, buf);
        }
    }

    private void _print(char[] s, byte[] buf) {
        int len = s.length * 2;
        int j = 0;
        for (int i = 0; i < s.length; i++) {
            char c = s[i];
            buf[j++] = (byte) (c & 0xFF);
            c >>= 1;
            buf[j++] = (byte) c;
        }
        _write(buf, 0, len);
    }

    public void print(double d) {
        print(Double.doubleToLongBits(d));
    }

    public void print(float f) {
        print(Float.floatToIntBits(f));
    }

    public void print(Object obj) {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(buf);
            out.writeObject(obj);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        print(buf.toByteArray());
    }

}
