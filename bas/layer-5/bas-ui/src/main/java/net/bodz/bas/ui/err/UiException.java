package net.bodz.bas.ui.err;

public class UiException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public UiException() {
        super();
    }

    public UiException(String message, Throwable cause) {
        super(message, cause);
    }

    public UiException(String message) {
        super(message);
    }

    public UiException(Throwable cause) {
        super(cause);
    }

}
