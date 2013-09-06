package net.bodz.bas.io.bit;

import java.io.IOException;

public interface ISimpleBitOut {

    /**
     * @param bit
     *            only the lowest bit is written, the higher 31 bits are ignored.
     */
    void writeBit(int bit)
            throws IOException;

}
