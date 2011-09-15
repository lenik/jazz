package net.bodz.bas.util.arch;

public interface IExecutableX<X extends Exception> {

    void execute()
            throws X;

}
