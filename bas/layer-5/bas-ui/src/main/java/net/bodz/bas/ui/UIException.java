package net.bodz.bas.ui;

public class UIException extends Exception {

    private static final long serialVersionUID = -2958503691436447996L;

    public UIException() {
        super();
    }

    public UIException(String message, Throwable cause) {
        super(message, cause);
    }

    public UIException(String message) {
        super(message);
    }

    public UIException(Throwable cause) {
        super(cause);
    }

}
