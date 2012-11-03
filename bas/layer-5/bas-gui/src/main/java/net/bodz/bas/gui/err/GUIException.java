package net.bodz.bas.gui.err;

public class GUIException
        extends Exception {

    private static final long serialVersionUID = 1L;

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
