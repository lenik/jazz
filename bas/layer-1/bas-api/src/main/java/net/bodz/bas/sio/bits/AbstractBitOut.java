package net.bodz.bas.sio.bits;

import java.io.IOException;

import net.bodz.bas.util.bits.Byte_leBits;
import net.bodz.bas.util.bits.ISimpleBits;
import net.bodz.bas.util.bits.Int_leBits;

public abstract class AbstractBitOut
        implements IBitOut {

    @Override
    public void write(ISimpleBits bits)
            throws IOException {
        int size = bits.size();
        for (int i = 0; i < size; i++)
            _write(bits.test(i));
    }

    public abstract void _write(boolean bit)
            throws IOException;

    /**
     * In little-endian version.
     * 
     * @throws IOException
     * 
     * @see Byte_leBits
     */
    public void print(byte[] bytes, int bitOff, int bits)
            throws IOException {
        write(new Byte_leBits(bytes, bitOff, bits));
    }

    /**
     * In little-endian version.
     * 
     * @throws IOException
     * 
     * @see Int_leBits
     */
    public void print(int[] ints, int bitOff, int bits)
            throws IOException {
        write(new Int_leBits(ints, bitOff, bits));
    }

    @Override
    public void close()
            throws IOException {
    }

}
