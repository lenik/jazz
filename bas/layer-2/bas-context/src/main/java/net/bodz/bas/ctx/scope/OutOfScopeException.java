package net.bodz.bas.ctx.scope;

public class OutOfScopeException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OutOfScopeException() {
        super();
    }

    public OutOfScopeException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfScopeException(String message) {
        super(message);
    }

    public OutOfScopeException(Throwable cause) {
        super(cause);
    }

}
