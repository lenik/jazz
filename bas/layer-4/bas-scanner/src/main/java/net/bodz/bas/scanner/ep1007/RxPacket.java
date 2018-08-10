package net.bodz.bas.scanner.ep1007;

import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.c.java.util.Arrays;

public class RxPacket {

    private final InputStream in;
    private int checksum;

    private int length;
    private int pos;

    private int error;
    private boolean beep;

    private RxPacket(InputStream in)
            throws IOException {
        this.in = in;
    }

    public int getErrorCode() {
        return error;
    }

    public boolean isBeep() {
        return beep;
    }

    public static RxPacket start(InputStream in, TxPacket tx)
            throws IOException, Ep1007Exception {
        RxPacket pkt = new RxPacket(in);
        pkt._start();
        return pkt;
    }

    void _start()
            throws IOException, Ep1007Exception {
        length = in.read();

        int src = readByte();
        if (src != MessageEndpoint.DECODER.code)
            throw new Ep1007Exception("Expect DECODER, but got: " + src);

        int dst = readByte();
        if (dst != MessageEndpoint.HOST.code)
            throw new Ep1007Exception("Expect HOST, but got: " + dst);

        readByte(); // reserved
        readByte(); // reserved

        int error = readByte();
        RxErrorCode errorCode = RxErrorCode.forCode(error);
        if (errorCode != RxErrorCode.SetOK)
            throw new Ep1007Exception(error, String.format(//
                    "Error response: %d.", error));
    }

    public int readByte()
            throws IOException {
        if (pos >= length)
            throw new IllegalStateException("Read after EOF.");
        pos++;
        int byt = in.read();
        checksum += byt;
        return byt;
    }

    public int readWord()
            throws IOException {
        int hi = readByte();
        int lo = readByte();
        int word = ((hi & 0xFF) << 8) | (lo & 0xFF);
        return word;
    }

    public int readDword()
            throws IOException {
        int dword = 0;
        for (int i = 0; i < 4; i++) {
            int byt = readByte();
            dword <<= 8;
            dword |= (byt & 0xFF);
            checksum += byt;
        }
        return dword;
    }

    public final int read(byte[] bytes)
            throws IOException {
        return read(bytes, 0, bytes.length);
    }

    public int read(byte[] bytes, int off, int len)
            throws IOException {
        int maxlen = length - pos;
        if (len > maxlen)
            len = maxlen;
        int got = 0;
        while (got < len) {
            int cb = in.read(bytes, off + got, len - got);
            if (cb == -1)
                break;
            got += cb;
        }
        pos += got;
        checksum += Arrays.sum(bytes, off, off + got);
        return got;
    }

    public void readChecksumAndVerify()
            throws IOException {
        int expected = (~checksum + 1) & 0xFFFF;
        int actual = readWord() & 0xFFFF;
        if (expected != actual)
            throw new IOException(String.format("Checksum failed: expect %d, but read %d.", ~checksum, actual));
    }

    public void end()
            throws IOException, Ep1007Exception {
        int beep = readByte();
        this.beep = beep == 0x31;
        readChecksumAndVerify();
    }

}
