package net.bodz.bas.mem;

public class AccessException extends Exception {

    private static final long serialVersionUID = -4035080963915624612L;

    public AccessException() {
        super();
    }

    public AccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessException(String message) {
        super(message);
    }

    public AccessException(Throwable cause) {
        super(cause);
    }

}
