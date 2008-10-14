package net.bodz.bas.gui;

public class GUIRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -1692366451990845110L;

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
