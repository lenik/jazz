package net.bodz.bas.lang.err;

public class CreateException extends Exception {

    private static final long serialVersionUID = -6911050115778312930L;

    public CreateException() {
        super();
    }

    public CreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateException(String message) {
        super(message);
    }

    public CreateException(Throwable cause) {
        super(cause);
    }

}
