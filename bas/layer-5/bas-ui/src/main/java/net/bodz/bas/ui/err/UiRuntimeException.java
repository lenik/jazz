package net.bodz.bas.ui.err;

public class UiRuntimeException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UiRuntimeException() {
        super();
    }

    public UiRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UiRuntimeException(String message) {
        super(message);
    }

    public UiRuntimeException(Throwable cause) {
        super(cause);
    }

}
