package net.bodz.bas.exceptions;

public class CompileException extends Exception {

    private static final long serialVersionUID = 5116598465290015857L;

    public CompileException() {
        super();
    }

    public CompileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompileException(String message) {
        super(message);
    }

    public CompileException(Throwable cause) {
        super(cause);
    }

}
