package net.bodz.bas.bits;

public interface ISimpleBits {

    int size();

    boolean test(int bitIndex);

    void set(int bitIndex, boolean value);

}
