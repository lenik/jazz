package net.bodz.bas.model;

public interface IExecutableX<X extends Exception> {

    void execute()
            throws X;

}
