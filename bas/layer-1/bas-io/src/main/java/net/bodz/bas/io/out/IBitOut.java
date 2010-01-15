package net.bodz.bas.io.out;

import java.io.IOException;

import net.bodz.bas.bits.ISimpleBits;

public interface IBitOut {

    void write(ISimpleBits bits) throws IOException;

}
