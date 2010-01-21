package net.bodz.bas.sio;

import java.io.IOException;

import net.bodz.bas.bits.ISimpleBits;

public interface IBitIn {

    /**
     * @return 0 or 1 if read succeeded, or -1 if EOF reached.
     */
    int readBit()
            throws IOException;

    /**
     * @return bit count successfully read, -1 if EOF reached.
     */
    int readBit(ISimpleBits bits)
            throws IOException;

    void close()
            throws IOException;

}
