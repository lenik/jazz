package net.bodz.bas.t._bit;

public class Byte_leBits
        extends AbstractBits {

    private byte[] bytes;
    private int bits;

    public Byte_leBits(int bits) {
        this(new byte[units(bits, Byte.SIZE)], bits);
    }

    public Byte_leBits(byte... bytes) {
        this(bytes, bytes.length * Byte.SIZE);
    }

    public Byte_leBits(byte[] bytes, int bits) {
        assert bits <= bytes.length * Byte.SIZE;
        this.bytes = bytes;
        this.bits = bits;
    }

    public Byte_leBits(byte[] bytes, int bitOff, int bits) {
        if (bitOff == 0) {
            this.bytes = bytes;
            this.bits = bits;
        } else {
            this.bytes = new byte[units(bits, Byte.SIZE)];
            this.bits = bits;
            set(bytes, bitOff, 0, bits);
        }
    }

    public static final byte[] SET;
    public static final byte[] CLEAR;
    static {
        SET = new byte[] {
                //
                (byte) 0x01, (byte) 0x02, (byte) 0x04, (byte) 0x08, //
                (byte) 0x10, (byte) 0x20, (byte) 0x40, (byte) 0x80, };
        CLEAR = new byte[] {
                //
                (byte) 0xfe, (byte) 0xfd, (byte) 0xfb, (byte) 0xf7, //
                (byte) 0xef, (byte) 0xdf, (byte) 0xbf, (byte) 0x7f, };
    }

    @Override
    public int size() {
        return bits;
    }

    @Override
    public boolean test(int bitIndex) {
        int off = bitIndex / Byte.SIZE;
        bitIndex %= Byte.SIZE;
        return (bytes[off] & SET[bitIndex]) != 0;
    }

    @Override
    public void set(int bitIndex, boolean value) {
        int off = bitIndex / Byte.SIZE;
        bitIndex %= Byte.SIZE;
        if (value)
            bytes[off] |= SET[bitIndex];
        else
            bytes[off] &= CLEAR[bitIndex];
    }

}
