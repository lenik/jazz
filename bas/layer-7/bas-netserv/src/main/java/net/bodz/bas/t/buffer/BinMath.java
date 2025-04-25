package net.bodz.bas.t.buffer;

public class BinMath {

    /**
     * compute the capacity aligned to 2^n.
     */
    public static int minPowerOf2GreaterThanOrEquals(int required) {
        int min = 1;
        while (min < required) {
            min <<= 1;
            if (min == 0x8000_0000)
                throw new IllegalArgumentException("Required too big capacity: " + required);
        }
        assert min >= required;
        return min;
    }

}
