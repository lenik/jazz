package net.bodz.bas.closure;

public interface IExecutable<X extends Throwable> {

    void execute() throws X;

}
