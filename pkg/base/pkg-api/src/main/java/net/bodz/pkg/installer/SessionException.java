package net.bodz.pkg.installer;

public class SessionException
        extends Exception {

    private static final long serialVersionUID = 1233952946545728221L;

    public SessionException() {
        super();
    }

    public SessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public SessionException(String message) {
        super(message);
    }

    public SessionException(Throwable cause) {
        super(cause);
    }

}
