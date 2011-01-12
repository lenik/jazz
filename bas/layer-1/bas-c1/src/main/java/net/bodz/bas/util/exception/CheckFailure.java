package net.bodz.bas.util.exception;

public class CheckFailure
        extends Error {

    private static final long serialVersionUID = 1L;

    public CheckFailure() {
        super();
    }

    public CheckFailure(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckFailure(String message) {
        super(message);
    }

    public CheckFailure(Throwable cause) {
        super(cause);
    }

}
