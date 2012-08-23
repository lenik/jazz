package net.bodz.bas.gui.ia;

public class UIException
        extends Exception {

    private static final long serialVersionUID = 1L;

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
