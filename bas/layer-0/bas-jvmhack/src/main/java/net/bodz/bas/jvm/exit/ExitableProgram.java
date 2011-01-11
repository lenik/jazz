package net.bodz.bas.jvm.exit;

public interface ExitableProgram<X extends Exception> {

    void execute()
            throws X;

}
