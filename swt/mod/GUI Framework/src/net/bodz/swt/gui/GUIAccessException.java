package net.bodz.swt.gui;

import net.bodz.bas.gui.GUIRuntimeException;

public class GUIAccessException extends GUIRuntimeException {

    private static final long serialVersionUID = -8443681027647625075L;

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
