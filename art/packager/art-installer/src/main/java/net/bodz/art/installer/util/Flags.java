package net.bodz.art.installer.util;

public class Flags {

    int flags;

    public void set(int bits) {
        flags |= bits;
    }

    public void clear(int bits) {
        flags &= ~bits;
    }

    /**
     * @return <code>true</code> if bits masked by <code>mask</code> are equals
     *         to the specified <code>bits</code>.
     */
    public boolean isSet(int mask, int bits) {
        return (flags & mask) == bits;
    }

    /**
     * @return <code>true</code> if any bit specified by <code>bits</code> is
     *         set.
     */
    public boolean isSet(int bits) {
        return (flags & bits) != 0;
    }

    @Override
    public String toString() {
        String bin = Integer.toBinaryString(flags);
        return bin;
    }

}
