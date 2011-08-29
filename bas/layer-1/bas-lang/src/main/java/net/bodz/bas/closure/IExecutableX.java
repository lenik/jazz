package net.bodz.bas.closure;

public interface IExecutableX<X extends Exception> {

    void execute()
            throws X;

}
