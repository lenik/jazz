package net.bodz.bas.io;

import java.io.IOException;

import net.bodz.bas.math.IBits;
import net.bodz.bas.types.Bits.BytevLE;
import net.bodz.bas.types.Bits.IntvLE;

public abstract class BitOut implements IBitOut {

    @Override
    public void write(IBits bits) throws IOException {
        int size = bits.size();
        for (int i = 0; i < size; i++)
            _write(bits.test(i));
    }

    public abstract void _write(boolean bit) throws IOException;

    /**
     * In little-endian version.
     * 
     * @throws IOException
     * 
     * @see BytevLE
     */
    public void print(byte[] bytes, int bitOff, int bits) throws IOException {
        write(new BytevLE(bytes, bitOff, bits));
    }

    /**
     * In little-endian version.
     * 
     * @throws IOException
     * 
     * @see IntvLE
     */
    public void print(int[] ints, int bitOff, int bits) throws IOException {
        write(new IntvLE(ints, bitOff, bits));
    }

}
