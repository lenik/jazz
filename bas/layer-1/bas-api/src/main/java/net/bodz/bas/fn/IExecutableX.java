package net.bodz.bas.fn;

@FunctionalInterface
public interface IExecutableX<X extends Throwable> {

    void execute()
            throws X;

}
