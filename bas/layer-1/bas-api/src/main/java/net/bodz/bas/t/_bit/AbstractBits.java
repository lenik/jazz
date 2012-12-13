package net.bodz.bas.t._bit;

public abstract class AbstractBits
        implements IBits {

    protected static int units(int bits, int unitSize) {
        return (bits + unitSize - 1) / unitSize;
    }

    protected int units(int unitSize) {
        return (size() + unitSize - 1) / unitSize;
    }

    @Override
    public int get(int bitIndex) {
        return test(bitIndex) ? 1 : 0;
    }

    @Override
    public void set(int bitIndex, int value) {
        set(bitIndex, value != 0);
    }

    @Override
    public void set(int bitIndex) {
        set(bitIndex, 1);
    }

    @Override
    public void clear(int bitIndex) {
        set(bitIndex, 0);
    }

    @Override
    public void get(boolean[] buf, int off, int bitStart, int bitEnd) {
        for (int i = bitStart; i < bitEnd; i++)
            buf[off++] = test(i);
    }

    @Override
    public void set(boolean[] buf, int off, int bitStart, int bitEnd) {
        for (int i = bitStart; i < bitEnd; i++)
            set(i, buf[off++]);
    }

    /**
     * Implemented in little-endian version.
     * 
     * @see BytevLE
     */
    @Override
    public void get(byte[] buf, int bitOff, int bitStart, int bitEnd) {
        int off = bitOff / Byte.SIZE;
        bitOff %= Byte.SIZE;
        for (int i = bitStart; i < bitEnd; i++) {
            if (test(i))
                buf[off] |= Byte_leBits.SET[bitOff];
            else
                buf[off] &= Byte_leBits.CLEAR[bitOff];
            if (++bitOff >= Byte.SIZE) {
                off++;
                bitOff = 0;
            }
        }
    }

    /**
     * Implemented in little-endian version.
     * 
     * @see Int_leBits
     */
    @Override
    public void get(int[] buf, int bitOff, int bitStart, int bitEnd) {
        int off = bitOff / Integer.SIZE;
        bitOff %= Integer.SIZE;
        for (int i = bitStart; i < bitEnd; i++) {
            if (test(i))
                buf[off] |= Int_leBits.SET[bitOff];
            else
                buf[off] &= Int_leBits.CLEAR[bitOff];
            if (++bitOff >= Integer.SIZE) {
                off++;
                bitOff = 0;
            }
        }
    }

    /**
     * Implemented in little-endian version.
     * 
     * @see BytevLE
     */
    @Override
    public void set(byte[] buf, int bitOff, int bitStart, int bitEnd) {
        int off = bitOff / Byte.SIZE;
        bitOff %= Byte.SIZE;
        for (int i = bitStart; i < bitEnd; i++) {
            set(i, (buf[off] & Byte_leBits.SET[bitOff]) != 0);
            if (++bitOff >= Byte.SIZE) {
                off++;
                bitOff = 0;
            }
        }
    }

    /**
     * Implemented in little-endian version.
     * 
     * @see Int_leBits
     */
    @Override
    public void set(int[] buf, int bitOff, int bitStart, int bitEnd) {
        int off = bitOff / Integer.SIZE;
        bitOff %= Integer.SIZE;
        for (int i = bitStart; i < bitEnd; i++) {
            set(i, (buf[off] & Int_leBits.CLEAR[bitOff]) != 0);
            if (++bitOff >= Integer.SIZE) {
                off++;
                bitOff = 0;
            }
        }
    }

    @Override
    public boolean[] toBooleanArray() {
        int bits = size();
        boolean[] v = new boolean[bits];
        get(v, 0, 0, bits);
        return v;
    }

    @Override
    public byte[] toByteArray(boolean padOne) {
        int bits = size();
        if (bits == 0)
            return new byte[0];
        int bytes = units(Byte.SIZE);
        byte[] v = new byte[bytes];
        if (padOne)
            v[bytes - 1] = -1;
        get(v, 0, 0, bits);
        return v;
    }

    @Override
    public int[] toIntArray(boolean padOne) {
        int bits = size();
        if (bits == 0)
            return new int[0];
        int ints = units(Integer.SIZE);
        int[] v = new int[ints];
        if (padOne)
            v[ints - 1] = -1;
        get(v, 0, 0, bits);
        return v;
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ size();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IBits))
            return false;
        IBits b = (IBits) obj;
        int size = size();
        if (size != b.size())
            return false;
        for (int i = 0; i < size; i++)
            if (test(i) != b.test(i))
                return false;
        return true;
    }

    public String draw(int width, char zero, char one) {
        int size = size();
        StringBuilder buf = new StringBuilder( //
                size * (width + 2) / width + 2);
        for (int i = 0; i < size; i++) {
            buf.append(test(i) ? one : zero);
            if (i % width == (width - 1))
                buf.append('\n');
        }
        return buf.toString();
    }

    static boolean BITMAP_STRING = true;

    @Override
    public String toString() {
        if (BITMAP_STRING)
            return draw(16, '.', '*');
        int size = size();
        StringBuilder buf = new StringBuilder(size);
        for (int i = 0; i < size; i++)
            buf.append(test(i) ? '1' : '0');
        return buf.toString();
    }

}
