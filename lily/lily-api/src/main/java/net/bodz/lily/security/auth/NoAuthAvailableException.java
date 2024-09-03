package net.bodz.lily.security.auth;

public class NoAuthAvailableException
        extends AuthException {

    private static final long serialVersionUID = 1L;

    public NoAuthAvailableException() {
        super();
    }

    public NoAuthAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAuthAvailableException(String message) {
        super(message);
    }

    public NoAuthAvailableException(Throwable cause) {
        super(cause);
    }

}
