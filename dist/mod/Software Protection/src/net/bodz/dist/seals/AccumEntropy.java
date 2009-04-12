package net.bodz.dist.seals;

/**
 * @test AccumEntropyTest
 */
public class AccumEntropy extends _Entropy {

    private byte[]    pool;
    private final int multiplier;

    public AccumEntropy() {
        this(16, 97);
    }

    public AccumEntropy(int poolSize, int multiplier) {
        if (poolSize < 2)
            throw new IllegalArgumentException("The pool is too small");
        this.pool = new byte[poolSize];
        this.multiplier = multiplier;
    }

    @Override
    public void drop(byte b) {
        assert pool.length >= 2;
        // treat the pool as a big integer in little endian.
        int carry = b;
        for (int i = 0; i < pool.length; i++) {
            int x = ((pool[i] & 0xff) * multiplier + carry);
            carry = x >> 8;
            pool[i] = (byte) x;
        }
    }

    @Override
    protected byte[] getPool() {
        return pool;
    }

}
