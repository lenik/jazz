package net.bodz.bas.io.out;

import java.io.IOException;

import net.bodz.bas.bits.ISimpleBits;

public class BitOuts {

    public static final BitOut nil;
    static {
        nil = new BitOut() {
            @Override
            public void write(ISimpleBits bits) throws IOException {
            }

            @Override
            public void _write(boolean bit) throws IOException {
            }
        };
    }

}
