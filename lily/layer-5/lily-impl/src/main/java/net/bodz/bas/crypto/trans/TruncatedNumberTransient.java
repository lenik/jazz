package net.bodz.bas.crypto.trans;

import net.bodz.bas.crypto.trans.fn.ICodeBin;

@Deprecated
public class TruncatedNumberTransient
        extends AbstractFlyingTransient {

    static final int DEFAULT_LENGTH = 6;

    final int keyHash;

    int length;
    long modulo;
    int radix = 10;

    public TruncatedNumberTransient(Object key, long window) {
        this(key, window, DEFAULT_LENGTH);
    }

    public TruncatedNumberTransient(Object key, long window, int length) {
        super(window);
        if (key == null)
            throw new NullPointerException("key");
        this.keyHash = key.hashCode();
        length(length);
    }

    public TruncatedNumberTransient length(int length) {
        this.length = length;
        this.modulo = pow(10, length);
        return this;
    }

    static long pow(long x, int y) {
        long result = 1;
        while (y > 0) {
            if ((y & 1) == 0) {
                x *= x;
                y >>>= 1;
            } else {
                result *= x;
                y--;
            }
        }
        return result;
    }

    public long getCodeNum(long index) {
        final int prime = 45077;
        long hash = index * prime;
        hash ^= keyHash;
        return hash;
    }

    @Override
    public ICodeBin getCode(long index) {
        long hash = getCodeNum(index);
        return new TruncatedNumberBin(length, hash);
    }

}
