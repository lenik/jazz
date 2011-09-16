package net.bodz.bas.util.bits;

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

}
