package net.bodz.lily.security.login.key;


public class SimpleFlyingCode
        extends AbstractFlyingCode {

    static final int DEFAULT_LENGTH = 6;

    static final int prime = 45077;

    final int keyHash;

    int length;
    long modulo;
    int radix = 10;

    public SimpleFlyingCode(Object key, long window) {
        this(key, window, DEFAULT_LENGTH);
    }

    public SimpleFlyingCode(Object key, long window, int length) {
        super(window);
        if (key == null)
            throw new NullPointerException("key");
        this.keyHash = key.hashCode();
        length(length);
    }

    SimpleFlyingCode length(int len) {
        this.length = len;
        long m = 1;
        for (int i = 0; i < len; i++)
            m *= 10;
        this.modulo = m;
        return this;
    }

    @Override
    public String getCode(long index) {
        long hash = index * prime;
        hash ^= keyHash;
        return format(hash);
    }

    String format(long hash) {
        String s = Long.toString(hash, radix);
        int n = s.length();
        if (n > length)
            return s.substring(n - length);
        StringBuilder sb = new StringBuilder();
        for (int i = n; i < length; i++)
            sb.append('0');
        sb.append(s);
        return sb.toString();
    }

}
