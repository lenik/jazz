package net.bodz.lily.security.login;

public abstract class ModifiedChallenger
        extends AbstractTimeoutChallenger {

    ITimeoutChallenger orig;

    public ModifiedChallenger(ITimeoutChallenger orig) {
        super(orig.getWindow());
        this.orig = orig;
    }

    @Override
    public String getCode(long index) {
        String origCode = orig.getCode(index);
        return modify(origCode);
    }

    protected abstract String modify(String code);

}
