package net.bodz.bas.err;

public abstract class ProcessException
        extends Exception {

    public static final long serialVersionUID = 1L;

    public ProcessException() {
        super();
    }

    public ProcessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessException(String message) {
        super(message);
    }

    public ProcessException(Throwable cause) {
        super(cause);
    }

}
