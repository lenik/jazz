package net.bodz.bas.ui.model.action;

public class RollbackException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public RollbackException() {
        super();
    }

    public RollbackException(String message, Throwable cause) {
        super(message, cause);
    }

    public RollbackException(String message) {
        super(message);
    }

    public RollbackException(Throwable cause) {
        super(cause);
    }

}
