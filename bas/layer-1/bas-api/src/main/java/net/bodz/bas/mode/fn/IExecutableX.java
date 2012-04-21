package net.bodz.bas.mode.fn;

public interface IExecutableX<X extends Exception> {

    void execute()
            throws X;

}
