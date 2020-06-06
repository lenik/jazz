package net.bodz.lily.security.login;

public class SaltGenerator
        extends AbstractTimeoutChallenger {

    static final int NSLOT = 3;
    static final int DEFAULT_WINDOW = 10_000; // 10 second

    static final int prime = 45077;

    public SaltGenerator() {
        this(DEFAULT_WINDOW);
    }

    public SaltGenerator(long window) {
        super(window, NSLOT);
    }

    @Override
    protected String mix(long index) {
        long hash = index * prime;
        hash %= 1_000_000_000;
        return String.valueOf(hash);
    }

}
