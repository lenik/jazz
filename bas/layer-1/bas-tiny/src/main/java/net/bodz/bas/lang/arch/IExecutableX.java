package net.bodz.bas.lang.arch;

public interface IExecutableX<X extends Exception> {

    void execute()
            throws X;

}
