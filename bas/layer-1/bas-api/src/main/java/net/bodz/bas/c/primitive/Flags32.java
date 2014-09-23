package net.bodz.bas.c.primitive;

import java.io.Serializable;

public class Flags32
        implements Serializable {

    private static final long serialVersionUID = 1L;

    public int value;

    public Flags32() {
    }

    public Flags32(int flags) {
        this.value = flags;
    }

    public final void init(Flags32 flags32) {
        this.value = flags32.value;
    }

    public final void init(int bits) {
        this.value = bits;
    }

    public final void set(int mask) {
        this.value |= mask;
    }

    public final void setOrClear(int mask, boolean condition) {
        if (condition)
            this.value |= mask;
        else
            this.value &= ~mask;
    }

    public static int setOrClear(int value, int mask, boolean condition) {
        if (condition)
            value |= mask;
        else
            value &= ~mask;
        return value;
    }

    public final void clear(int mask) {
        this.value &= ~mask;
    }

    public final void clearOrSet(int mask, boolean condition) {
        if (condition)
            this.value &= ~mask;
        else
            this.value |= mask;
    }

    public static int clearOrSet(int value, int mask, boolean condition) {
        if (condition)
            value &= ~mask;
        else
            value |= mask;
        return value;
    }

    public final void toggle(int mask) {
        this.value ^= mask;
    }

    /**
     * Check any bit in the mask is set.
     */
    public final boolean test(int mask) {
        return containsAny(mask);
    }

    public final boolean contains(int mask) {
        return (value & mask) == mask;
    }

    public final boolean containsAny(int mask) {
        return (value & mask) != 0;
    }

    public final boolean checkAndLoad(int mask) {
        return checkAndSet(mask);
    }

    public final boolean checkAndUnload(int mask) {
        return checkAndClear(mask);
    }

    public final boolean checkAndSet(int mask) {
        if ((value & mask) == mask)
            return false;
        value |= mask;
        return true;
    }

    public final boolean checkAndClear(int mask) {
        if ((value & mask) == 0)
            return false;
        value &= ~mask;
        return true;
    }

    public final int translate(int... testAndSet) {
        int bits = 0;
        int i = 0;
        while (i < testAndSet.length) {
            int test = testAndSet[i++];
            int set = testAndSet[i++];
            if (contains(test))
                bits |= set;
        }
        return bits;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Flags32.class)
            return value == ((Flags32) obj).value;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return value * 30031;
    }

    @Override
    public String toString() {
        if (value == 0)
            return "0";

        // char[32]: needs to fast check the MSB index.
        StringBuilder buffer = new StringBuilder();
        int x = value;
        while (x != 0) {
            int lsb = x & 1;
            x >>= 1;
            buffer.append((char) ('0' + lsb));
        }
        return buffer.reverse().toString();
    }

}