package net.bodz.bas.api.types;

public interface Executable<X extends Throwable> {

    void execute() throws X;

}
