package net.bodz.bas.gui.err;

public class GUIAccessException
        extends GUIRuntimeException {

    private static final long serialVersionUID = 1L;

    public GUIAccessException() {
        super();
    }

    public GUIAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public GUIAccessException(String message) {
        super(message);
    }

    public GUIAccessException(Throwable cause) {
        super(cause);
    }

}
