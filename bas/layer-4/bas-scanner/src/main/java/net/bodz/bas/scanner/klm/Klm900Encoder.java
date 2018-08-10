package net.bodz.bas.scanner.klm;

import java.io.IOException;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.io.IByteOut;

public class Klm900Encoder {

    public static final int T_CMD = 0;
    public static final int T_RESPONSE = 1;
    public static final int T_NOTIFY = 2;

    private final IByteOut out;
    private int checksum;
    private boolean end;

    public Klm900Encoder(IByteOut out)
            throws IOException {
        this(out, T_CMD);
    }

    public Klm900Encoder(IByteOut out, boolean responseOrNotify)
            throws IOException {
        this(out, responseOrNotify ? T_RESPONSE : T_NOTIFY);
    }

    public Klm900Encoder(IByteOut out, int type)
            throws IOException {
        this.out = out;
        out.write(0xBB); // SOT
        write(type);
    }

    public void end()
            throws IOException {
        if (end)
            throw new IllegalStateException("Already end.");
        out.write(checksum);
        out.write(0x7E); // EOT
        end = true;
    }

    public void parameter(int byt)
            throws IOException {
        write2(1);
        write(byt);
    }

    public void parameter2(int word)
            throws IOException {
        write2(2);
        write2(word);
    }

    public void parameter4(int dword)
            throws IOException {
        write2(4);
        write4(dword);
    }

    public void parameter(byte[] buf)
            throws IOException {
        parameter(buf, 0, buf.length);
    }

    public void parameter(byte[] buf, int off, int len)
            throws IOException {
        write2(len);
        write(buf, off, len);
    }

    public void write(int byt)
            throws IOException {
        out.write(byt);
        checksum += byt;
    }

    public void write2(int word)
            throws IOException {
        int hi = (word >> 8) & 0xFF;
        int lo = (word & 0xFF);
        out.write(hi);
        out.write(lo);
        checksum += hi + lo;
    }

    public void write4(int dword)
            throws IOException {
        for (int i = 0; i < 4; i++) {
            int byt = (dword >> 24) & 0xFF;
            dword <<= 8;
            out.write(byt);
            checksum += byt;
        }
    }

    public void write(byte[] buf)
            throws IOException {
        write(buf, 0, buf.length);
    }

    public void write(byte[] buf, int off, int len)
            throws IOException {
        out.write(buf, off, len);
        checksum += Arrays.sum(buf, off, off + len);
    }

}
