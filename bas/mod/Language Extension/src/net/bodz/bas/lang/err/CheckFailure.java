package net.bodz.bas.lang.err;

public class CheckFailure extends Error {

    private static final long serialVersionUID = 1740896623654551464L;

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
