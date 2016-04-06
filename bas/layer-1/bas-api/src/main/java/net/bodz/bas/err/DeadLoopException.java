package net.bodz.bas.err;

public class DeadLoopException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DeadLoopException() {
        super();
    }

    public DeadLoopException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeadLoopException(String message) {
        super(message);
    }

    public DeadLoopException(Throwable cause) {
        super(cause);
    }

}
