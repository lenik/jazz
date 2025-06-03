package net.bodz.bas.make;

public class CompileException
        extends MakeException {

    private static final long serialVersionUID = -4993253465635521252L;

    public CompileException() {
    }

    public CompileException(String message) {
        super(message);
    }

    public CompileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompileException(Throwable cause) {
        super(cause);
    }

}
