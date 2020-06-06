package net.bodz.lily.security.login;

public class SecretChallenger
        extends AbstractTimeoutChallenger {

    static final int NSLOT = 10;
    SaltGenerator salts;
    String secret;

    public SecretChallenger(SaltGenerator salts, String secret, long window) {
        super(window, NSLOT);
        this.salts = salts;
        this.secret = secret;
    }

    @Override
    protected String mix(long index) {
        String salt = salts.mix(index);
        String s = salt + secret + salt;
        return null;
    }

}
