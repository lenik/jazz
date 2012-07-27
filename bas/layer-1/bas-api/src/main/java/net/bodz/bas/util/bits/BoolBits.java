package net.bodz.bas.util.bits;

public class BoolBits
        extends AbstractBits {

    private final boolean[] array;

    public BoolBits(int bits) {
        this.array = new boolean[bits];
    }

    public BoolBits(boolean... array) {
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
