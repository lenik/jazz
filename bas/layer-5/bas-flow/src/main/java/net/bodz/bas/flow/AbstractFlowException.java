package net.bodz.bas.flow;

public abstract class AbstractFlowException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public AbstractFlowException() {
        super();
    }

    public AbstractFlowException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractFlowException(String message) {
        super(message);
    }

    public AbstractFlowException(Throwable cause) {
        super(cause);
    }

}
