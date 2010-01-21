package net.bodz.bas.sio;

import java.io.IOException;

import net.bodz.bas.bits.ISimpleBits;

public interface IBitOut
        extends ISimpleBitOut {

    void write(ISimpleBits bits)
            throws IOException;

    void close()
            throws IOException;

}
