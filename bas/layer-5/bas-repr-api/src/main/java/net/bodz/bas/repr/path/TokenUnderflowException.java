package net.bodz.bas.repr.path;

public class TokenUnderflowException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TokenUnderflowException() {
        super();
    }

    public TokenUnderflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenUnderflowException(String message) {
        super(message);
    }

    public TokenUnderflowException(Throwable cause) {
        super(cause);
    }

}
