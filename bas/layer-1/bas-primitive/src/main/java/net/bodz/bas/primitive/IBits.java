package net.bodz.bas.primitive;

public interface IBits {

    int size();

    boolean test(int bitIndex);

    void set(int bitIndex, boolean value);

}
