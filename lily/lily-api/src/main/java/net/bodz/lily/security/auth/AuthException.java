package net.bodz.lily.security.auth;

public class AuthException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public AuthException() {
        super();
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthException(String message) {
        super(message);
    }

    public AuthException(Throwable cause) {
        super(cause);
    }

}
