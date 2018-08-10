package net.bodz.bas.scanner.ztx965n;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import net.bodz.bas.c.java.util.Arrays;

public class RxPacket {

    private final CommandCode command;
    private final InputStream in;
    private int checksum;

    private int length;
    private int pos;

    private boolean success;
    private int error;

    int droppedChars;
    int droppedPkts;

    private RxPacket(InputStream in, CommandCode command)
            throws IOException {
        this.in = in;
        this.command = command;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getErrorCode() {
        return error;
    }

    public CommandCode getCommand() {
        return command;
    }

    public static RxPacket start(InputStream in, TxPacket tx)
            throws IOException, Ztx965nException {
        return start(in, tx.getCommand());
    }

    public static RxPacket start(InputStream in, CommandCode command)
            throws IOException, Ztx965nException {
        RxPacket pkt = new RxPacket(in, command);
        pkt._start();
        return pkt;
    }

    void _start()
            throws IOException, Ztx965nException {
        int bootCode;
        while (true) {
            bootCode = in.read();
            switch (bootCode) {
            case 0xF0:
                success = true;
                break;
            case 0xF4:
                success = false;
                break;
            default:
                // unrelated packet. drop it.
                droppedChars++;
                continue;
            }

            length = in.read();
            pos = 0;
            checksum = bootCode + length;

            int cmd = readByte();
            if (cmd != command.getCode()) {
                // not the reply, drop it.
                readData();
                in.read(); // checksum
                droppedPkts++;
                continue;
            }
            break;
        }

        if (!success) {
            readDataAndCheck();
            RxErrorCode errorCode = RxErrorCode.forCode(error);
            throw new Ztx965nException(error, String.format(//
                    "Command %s failed: %s.", command, errorCode != null ? errorCode : error));
        }
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

    public int readAddress()
            throws IOException {
        int address = readByte();
        return address;
    }

    public byte[] readDataAndCheck()
            throws IOException {
        byte[] data = readData();

        int expected = (~checksum + 1) & 0xFF;
        int actual = readByte() & 0xFF;
        if (expected != actual)
            throw new IOException(String.format("Checksum failed: expect %d, but read %d.", ~checksum, actual));

        if (!success && data.length == 1)
            error = data[0] & 0xFF;

        return data;
    }

    public byte[] readData()
            throws IOException {
        int remaining = length - 1 - pos;
        if (remaining < 0)
            System.out.println("!!");
        byte[] data = new byte[remaining];
        int cb = read(data);
        if (cb != remaining)
            throw new IllegalStateException("Expect more data.");
        return data;
    }

    public boolean readChecksumAndCompare()
            throws IOException {
        int expected = (~checksum + 1) & 0xFF;
        int actual = readByte() & 0xFF;
        return expected == actual;
    }

    public Calendar readTime()
            throws IOException {
        int year = readByte();
        int month = readByte();
        int date = readByte();
        int hour = readByte();
        int minute = readByte();
        int second = readByte();
        Calendar time = Calendar.getInstance();
        time.set(year, month, date, hour, minute, second);
        return time;
    }

}
