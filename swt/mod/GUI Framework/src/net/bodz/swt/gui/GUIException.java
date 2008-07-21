package net.bodz.swt.gui;

public class GUIException extends Exception {

    private static final long serialVersionUID = -2958503691436447996L;

    public GUIException() {
        super();
    }

    public GUIException(String message, Throwable cause) {
        super(message, cause);
    }

    public GUIException(String message) {
        super(message);
    }

    public GUIException(Throwable cause) {
        super(cause);
    }

}
