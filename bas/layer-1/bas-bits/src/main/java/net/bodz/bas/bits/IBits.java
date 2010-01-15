package net.bodz.bas.bits;

public interface IBits
        extends ISimpleBits {

    int size();

    boolean test(int bitIndex);

    int get(int bitIndex);

    void set(int bitIndex, boolean value);

    void set(int bitIndex, int value);

    void set(int bitIndex);

    void clear(int bitIndex);

    void get(boolean[] buf, int off, int bitStart, int bitEnd);

    void get(byte[] buf, int bitOff, int bitStart, int bitEnd);

    void get(int[] buf, int bitOff, int bitStart, int bitEnd);

    void set(boolean[] buf, int off, int bitStart, int bitEnd);

    void set(byte[] buf, int bitOff, int bitStart, int bitEnd);

    void set(int[] buf, int bitOff, int bitStart, int bitEnd);

    boolean[] toBooleanArray();

    byte[] toByteArray(boolean padOne);

    int[] toIntArray(boolean padOne);

    public static class Boolv
            extends AbstractBits {

        private final boolean[] array;

        public Boolv(int bits) {
            this.array = new boolean[bits];
        }

        public Boolv(boolean... array) {
            this.array = array;
        }

        @Override
        public int size() {
            return array.length;
        }

        @Override
        public boolean test(int bitIndex) {
            return array[bitIndex];
        }

        @Override
        public void set(int bitIndex, boolean value) {
            array[bitIndex] = value;
        }

    }

    public static class BytevLE
            extends AbstractBits {

        private byte[] bytes;
        private int bits;

        public BytevLE(int bits) {
            this(new byte[units(bits, Byte.SIZE)], bits);
        }

        public BytevLE(byte... bytes) {
            this(bytes, bytes.length * Byte.SIZE);
        }

        public BytevLE(byte[] bytes, int bits) {
            assert bits <= bytes.length * Byte.SIZE;
            this.bytes = bytes;
            this.bits = bits;
        }

        public BytevLE(byte[] bytes, int bitOff, int bits) {
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

    public static class BytevBE
            extends AbstractBits {

        private byte[] bytes;
        private int bits;

        public BytevBE(int bits) {
            this(new byte[units(bits, Byte.SIZE)], bits);
        }

        public BytevBE(byte... bytes) {
            this(bytes, bytes.length * Byte.SIZE);
        }

        public BytevBE(byte[] bytes, int bits) {
            assert bits <= bytes.length * Byte.SIZE;
            this.bytes = bytes;
            this.bits = bits;
        }

        public BytevBE(byte[] bytes, int bitOff, int bits) {
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
                    (byte) 0x80, (byte) 0x40, (byte) 0x20, (byte) 0x10, //
                    (byte) 0x08, (byte) 0x04, (byte) 0x02, (byte) 0x01, };
            CLEAR = new byte[] {
            //
                    (byte) 0x7f, (byte) 0xbf, (byte) 0xdf, (byte) 0xef, //
                    (byte) 0xf7, (byte) 0xfb, (byte) 0xfd, (byte) 0xfe, };
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

    public static class IntvLE
            extends AbstractBits {

        private int[] ints;
        private int bits;

        public IntvLE(int bits) {
            this(new int[units(bits, Integer.SIZE)], bits);
        }

        public IntvLE(int... ints) {
            this(ints, ints.length * Integer.SIZE);
        }

        public IntvLE(int[] ints, int bits) {
            assert bits <= ints.length * Integer.SIZE;
            this.ints = ints;
            this.bits = bits;
        }

        public IntvLE(int[] ints, int bitOff, int bits) {
            if (bitOff == 0) {
                this.ints = ints;
                this.bits = bits;
            } else {
                this.ints = new int[units(bits, Integer.SIZE)];
                this.bits = bits;
                set(ints, bitOff, 0, bits);
            }
        }

        public static final int[] SET;
        public static final int[] CLEAR;
        static {
            SET = new int[] {
            //
                    0x00000001, 0x00000002, 0x00000004, 0x00000008, //
                    0x00000010, 0x00000020, 0x00000040, 0x00000080, //
                    0x00000100, 0x00000200, 0x00000400, 0x00000800, //
                    0x00001000, 0x00002000, 0x00004000, 0x00008000, //
                    0x00010000, 0x00020000, 0x00040000, 0x00080000, //
                    0x00100000, 0x00200000, 0x00400000, 0x00800000, //
                    0x01000000, 0x02000000, 0x04000000, 0x08000000, //
                    0x10000000, 0x20000000, 0x40000000, 0x80000000, //
            };
            CLEAR = new int[] {
            //
                    0xfffffffe, 0xfffffffd, 0xfffffffb, 0xfffffff7, //
                    0xffffffef, 0xffffffdf, 0xffffffbf, 0xffffff7f, //
                    0xfffffeff, 0xfffffdff, 0xfffffbff, 0xfffff7ff, //
                    0xffffefff, 0xffffdfff, 0xffffbfff, 0xffff7fff, //
                    0xfffeffff, 0xfffdffff, 0xfffbffff, 0xfff7ffff, //
                    0xffefffff, 0xffdfffff, 0xffbfffff, 0xff7fffff, //
                    0xfeffffff, 0xfdffffff, 0xfbffffff, 0xf7ffffff, //
                    0xefffffff, 0xdfffffff, 0xbfffffff, 0x7fffffff, //
            };
        }

        @Override
        public int size() {
            return bits;
        }

        @Override
        public boolean test(int bitIndex) {
            int off = bitIndex / Integer.SIZE;
            bitIndex %= Integer.SIZE;
            return (ints[off] & SET[bitIndex]) != 0;
        }

        @Override
        public void set(int bitIndex, boolean value) {
            int off = bitIndex / Integer.SIZE;
            bitIndex %= Integer.SIZE;
            if (value)
                ints[off] |= SET[bitIndex];
            else
                ints[off] &= CLEAR[bitIndex];
        }
    }

    public static class IntvBE
            extends AbstractBits {

        private int[] ints;
        private int bits;

        public IntvBE(int bits) {
            this(new int[units(bits, Integer.SIZE)], bits);
        }

        public IntvBE(int... ints) {
            this(ints, ints.length * Integer.SIZE);
        }

        public IntvBE(int[] ints, int bits) {
            assert bits <= ints.length * Integer.SIZE;
            this.ints = ints;
            this.bits = bits;
        }

        public IntvBE(int[] ints, int bitOff, int bits) {
            if (bitOff == 0) {
                this.ints = ints;
                this.bits = bits;
            } else {
                this.ints = new int[units(bits, Integer.SIZE)];
                this.bits = bits;
                set(ints, bitOff, 0, bits);
            }
        }

        public static final int[] SET;
        public static final int[] CLEAR;
        static {
            SET = new int[] {
            //
                    0x80000000, 0x40000000, 0x20000000, 0x10000000,//
                    0x08000000, 0x04000000, 0x02000000, 0x01000000,//
                    0x00800000, 0x00400000, 0x00200000, 0x00100000,//
                    0x00080000, 0x00040000, 0x00020000, 0x00010000,//
                    0x00008000, 0x00004000, 0x00002000, 0x00001000,//
                    0x00000800, 0x00000400, 0x00000200, 0x00000100,//
                    0x00000080, 0x00000040, 0x00000020, 0x00000010,//
                    0x00000008, 0x00000004, 0x00000002, 0x00000001,//
            };
            CLEAR = new int[] {
            //
                    0x7fffffff, 0xbfffffff, 0xdfffffff, 0xefffffff,//
                    0xf7ffffff, 0xfbffffff, 0xfdffffff, 0xfeffffff,//
                    0xff7fffff, 0xffbfffff, 0xffdfffff, 0xffefffff,//
                    0xfff7ffff, 0xfffbffff, 0xfffdffff, 0xfffeffff,//
                    0xffff7fff, 0xffffbfff, 0xffffdfff, 0xffffefff,//
                    0xfffff7ff, 0xfffffbff, 0xfffffdff, 0xfffffeff,//
                    0xffffff7f, 0xffffffbf, 0xffffffdf, 0xffffffef,//
                    0xfffffff7, 0xfffffffb, 0xfffffffd, 0xfffffffe,//
            };
        }

        @Override
        public int size() {
            return bits;
        }

        @Override
        public boolean test(int bitIndex) {
            int off = bitIndex / Integer.SIZE;
            bitIndex %= Integer.SIZE;
            return (ints[off] & SET[bitIndex]) != 0;
        }

        @Override
        public void set(int bitIndex, boolean value) {
            int off = bitIndex / Integer.SIZE;
            bitIndex %= Integer.SIZE;
            if (value)
                ints[off] |= SET[bitIndex];
            else
                ints[off] &= CLEAR[bitIndex];
        }
    }

}
