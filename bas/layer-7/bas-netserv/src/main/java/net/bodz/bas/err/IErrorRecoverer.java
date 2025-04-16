package net.bodz.bas.err;

public interface IErrorRecoverer {

    /**
     * @return true if recoverred.
     */
    boolean recoverError(Throwable e);

}
