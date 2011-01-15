package net.bodz.bas.bits;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class Int_beBitsTest
        extends Assert {

    // 00010010 00110100 01010110 01111000 10101011 11001101
    int[] va = { 0x12345678, 0xabcd0000, };

    @Test
    public void testIntv1() {
        // 00010010 00110100 01010110 01111000 10101011 11001101
        IBits bits = new Int_beBits(va, 32 + 16);
        // 01001000 00101100 01101010 00011110 11010101 10110011
        byte[] bv0 = { 0x48, 0x2c, 0x6a, 0x1e, (byte) 0xd5, (byte) 0xb3 };
        byte[] bv = bits.toByteArray(false);
        assertArrayEquals(bv0, bv);
    }

    @Test
    public void testIntv2() {
        // 0001001
        IBits bits = new Int_beBits(va, 7);
        // 0.1001000
        byte[] bv0 = { 0x48 };
        byte[] bv = bits.toByteArray(false);
        assertArrayEquals(bv0, bv);
        // 1.1001000
        byte[] bv1 = { (byte) 0xc8 };
        bv = bits.toByteArray(true);
        assertArrayEquals(bv1, bv);

        boolean[] boolv = bits.toBooleanArray();
        boolean[] bo0 = { false, false, false, true, false, false, true };
        assertTrue(Arrays.equals(bo0, boolv));
    }

}
