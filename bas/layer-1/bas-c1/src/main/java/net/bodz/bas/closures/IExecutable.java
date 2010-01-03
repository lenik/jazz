package net.bodz.bas.closures;

public interface IExecutable<X extends Throwable> {

    void execute() throws X;

}
