package net.bodz.bas.parser;

public interface IErrorRecoverer {

    /**
     * @return true if recoverred.
     */
    boolean recoverError(Throwable e);

}
