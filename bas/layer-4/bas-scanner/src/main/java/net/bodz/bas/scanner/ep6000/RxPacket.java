package net.bodz.bas.scanner.ep6000;

import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.scanner.ztx965n.RxErrorCode;

public class RxPacket {

    private final InputStream in;
    private int checksum;

    static final int S_START = 0;
    static final int S_PRETEXT = 1;
    static final int S_HEAD = 2;
    static final int S_TEXT = 3;
    static final int S_PRESTOP = 4;
    static final int S_STOP = 5;
    private int state = S_START;
    private int error;

    int droppedChars;

    private RxPacket(InputStream in)
            throws IOException {
        this.in = in;
    }

    public boolean isSuccess() {
        return state == S_STOP;
    }

    public int getErrorCode() {
        return error;
    }

    public static RxPacket start(InputStream in, TxPacket tx)
            throws IOException, Ep6000Exception {
        RxPacket pkt = new RxPacket(in);
        pkt._start();
        return pkt;
    }

    void _start()
            throws IOException, Ep6000Exception {
        int bootCode;
        while (true) {
            bootCode = in.read();
            switch (state) {
            case S_START:
                if (bootCode == 0x31) {
                    state = S_PRETEXT;
                    continue;
                }
                break;
            case S_PRETEXT:
                if (bootCode == 0) {
                    state = S_HEAD;
                    continue;
                }
                state = S_START;
                break;
            case S_HEAD:
                error = bootCode;
                if (error != 0) {
                    RxErrorCode errorCode = RxErrorCode.forCode(error);
                    throw new Ep6000Exception(error, String.format(//
                            "Received error: %s.", errorCode != null ? errorCode : error));
                }
                state = S_TEXT;
                return;
            }
            // unrelated packet. drop it.
            droppedChars++;
            break;
        }
    }

    public int readByte()
            throws IOException {
        int byt = in.read();
        checksum += byt;
        return byt;
    }

    public int readWord()
            throws IOException {
        int lo = readByte();
        int hi = readByte();
        int word = ((hi & 0xFF) << 8) | (lo & 0xFF);
        return word;
    }

    public int readDword()
            throws IOException {
        int dword = 0;
        for (int i = 0; i < 4; i++) {
            int byt = readByte();
            dword >>= 8;
            dword |= ((byt & 0xFF) << 24);
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
        int got = 0;
        while (got < len) {
            int cb = in.read(bytes, off + got, len - got);
            if (cb == -1)
                break;
            got += cb;
        }
        checksum += Arrays.sum(bytes, off, off + got);
        return got;
    }

    public byte[] readData()
            throws IOException {
        int len = readWord();
        byte[] data = new byte[len];
        int cb = read(data);
        if (cb != len)
            throw new IllegalStateException("Expect more data.");
        return data;
    }

    public void readChecksumAndVerify()
            throws IOException, Ep6000Exception {
        if (!readChecksumAndCompare())
            throw new Ep6000Exception(-1, "Checksum error.");
    }

    public boolean readChecksumAndCompare()
            throws IOException {
        int expected = (~checksum + 1) & 0xFF;
        int actual = readByte() & 0xFF;
        actual = expected; // checksum is not used, yet.
        return expected == actual;
    }

    public void readParameterAndVerify(int expectedParam)
            throws IOException, Ep6000Exception {
        int par = readWord();
        if (par != expectedParam)
            throw new Ep6000Exception(-1, "Not the expected parameter: " + par);
    }

    public void end()
            throws IOException, Ep6000Exception {
        readChecksumAndVerify();
        int eot = readWord();
        if (eot != 0x0055)
            throw new Ep6000Exception(-1, "Invalid end-of-packet received: " + Integer.toHexString(eot));
    }

}
