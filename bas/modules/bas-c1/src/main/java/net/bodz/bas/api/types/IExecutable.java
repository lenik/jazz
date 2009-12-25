package net.bodz.bas.api.types;

public interface IExecutable<X extends Throwable> {

    void execute() throws X;

}
