package net.bodz.bas.math;

public interface IBits {

    int size();

    boolean test(int bitIndex);

    void set(int bitIndex, boolean value);

}
