package net.bodz.bas.sio.bits;

import java.io.IOException;

import net.bodz.bas.t._bit.ISimpleBits;

public interface IBitOut
        extends ISimpleBitOut {

    void write(ISimpleBits bits)
            throws IOException;

    void close()
            throws IOException;

}
