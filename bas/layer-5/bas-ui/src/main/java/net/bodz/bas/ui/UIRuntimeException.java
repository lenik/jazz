package net.bodz.bas.ui;

public class UIRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -1692366451990845110L;

    public UIRuntimeException() {
        super();
    }

    public UIRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UIRuntimeException(String message) {
        super(message);
    }

    public UIRuntimeException(Throwable cause) {
        super(cause);
    }

}
