package net.bodz.bas.io.data;

import java.io.IOException;

public abstract class AbstractDataInBE
        extends AbstractDataIn {

    private final byte[] buf = new byte[8];

    @Override
    public boolean isBigEndian() {
        return true;
    }

    @Override
    public synchronized short readWord()
            throws IOException {
        int high = readByte() & 0xFF;
        int low = readByte() & 0xFF;
        return (short) ((high << 8) | low);
    }

    @Override
    public synchronized int readDword()
            throws IOException {
        readBytes(buf, 0, 4);
        return (buf[0] & 0xFF) << 24 //
                | (buf[1] & 0xFF) << 16 //
                | (buf[2] & 0xFF) << 8 //
                | (buf[3] & 0xFF);
    }

    @Override
    public synchronized long readQword()
            throws IOException {
        readBytes(buf, 0, 8);
        int high = (buf[0] & 0xFF) << 24 //
                | (buf[1] & 0xFF) << 16 //
                | (buf[2] & 0xFF) << 8 //
                | (buf[3] & 0xFF);
        int low = (buf[4] & 0xFF) << 24 //
                | (buf[5] & 0xFF) << 16 //
                | (buf[6] & 0xFF) << 8 //
                | (buf[7] & 0xFF);
        return (((long) low) & 0xFFFFFFFFL) //
                | ((((long) high) & 0xFFFFFFFFL) << 32);
    }

}
