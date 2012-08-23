package net.bodz.bas.gui.err;

public class GUIRuntimeException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public GUIRuntimeException() {
        super();
    }

    public GUIRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public GUIRuntimeException(String message) {
        super(message);
    }

    public GUIRuntimeException(Throwable cause) {
        super(cause);
    }

}
