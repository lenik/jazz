package net.bodz.bas.io;

import java.io.IOException;

@FunctionalInterface
public interface ISimpleByteOut {

    /**
     * @param b The byte to write, only lower 8 bits are written, the higher 24 bits are ignored.
     */
    void writeByte(int b)
            throws IOException;

}
