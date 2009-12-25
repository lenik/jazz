package net.bodz.bas.commons;

public interface IExecutable<X extends Throwable> {

    void execute() throws X;

}
