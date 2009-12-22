package net.bodz.bas.commons.math;

public interface IBits {

    int size();

    boolean test(int bitIndex);

    void set(int bitIndex, boolean value);

}
