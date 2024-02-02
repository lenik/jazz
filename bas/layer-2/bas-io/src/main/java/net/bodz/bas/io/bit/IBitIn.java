package net.bodz.bas.io.bit;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;

public interface IBitIn
        extends
            Closeable {

    /**
     * @return 0 or 1 if read succeeded, or -1 if EOF reached.
     */
    int readBit()
            throws IOException;

    default boolean readBool()
            throws IOException {
        int bit = readBit();
        if (bit == -1)
            throw new EOFException();
        return bit == 1 ? true : false;
    }

    int read()
            throws IOException;

    default int readInt(int n)
            throws IOException {
        return (int) readLong(n);
    }

    long readLong(int n)
            throws IOException;

    /**
     * @return bit count successfully read, -1 if EOF reached.
     */
//    int readBit(ISimpleBits bits)
//            throws IOException;

    int availableBits();

    void dropToByte();

    @Override
    void close()
            throws IOException;

}
