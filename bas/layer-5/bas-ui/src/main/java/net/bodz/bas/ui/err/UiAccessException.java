package net.bodz.bas.ui.err;

public class UiAccessException
        extends UiRuntimeException {

    private static final long serialVersionUID = 1L;

    public UiAccessException() {
        super();
    }

    public UiAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public UiAccessException(String message) {
        super(message);
    }

    public UiAccessException(Throwable cause) {
        super(cause);
    }

}
