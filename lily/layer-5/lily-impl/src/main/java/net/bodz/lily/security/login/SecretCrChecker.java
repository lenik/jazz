package net.bodz.lily.security.login;

public class SecretCrChecker
        extends AbstractTimeoutChallenger
        implements ISignatureChecker {

    static final int NSLOT = 3;
    static final int DEFAULT_WINDOW = 10_000; // 10 second

    static final int prime = 45077;

    public SecretCrChecker() {
        this(DEFAULT_WINDOW);
    }

    public SecretCrChecker(long window) {
        super(window, NSLOT);
    }

    @Override
    public String getCode(long index) {
        long hash = index * prime;
        hash %= 1_000_000_000;
        return String.valueOf(hash);
    }

    @Override
    public boolean checkSignature(Object data, Object newSignature) {
        String secret = (String) data;
        String cr = (String) newSignature;
        rcheck(cr);
        // plain
        return true;
    }

}
