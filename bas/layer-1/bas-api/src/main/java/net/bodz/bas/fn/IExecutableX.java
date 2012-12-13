package net.bodz.bas.fn;

public interface IExecutableX<X extends Exception> {

    void execute()
            throws X;

}
