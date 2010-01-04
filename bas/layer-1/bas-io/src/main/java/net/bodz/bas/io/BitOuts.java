package net.bodz.bas.io;

import java.io.IOException;

import net.bodz.bas.primitive.IBits;

public class BitOuts {

    public static final BitOut nil;
    static {
        nil = new BitOut() {
            @Override
            public void write(IBits bits) throws IOException {
            }

            @Override
            public void _write(boolean bit) throws IOException {
            }
        };
    }

}
