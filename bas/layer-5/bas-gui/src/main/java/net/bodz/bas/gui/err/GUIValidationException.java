package net.bodz.bas.gui.err;

import net.bodz.bas.err.CheckException;
import net.bodz.bas.gui.util.QuietHint;

public class GUIValidationException
        extends CheckException
        implements QuietHint {

    private static final long serialVersionUID = 1L;

    Object control;

    public GUIValidationException() {
        super();
    }

    public GUIValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public GUIValidationException(String message) {
        super(message);
    }

    public GUIValidationException(Throwable cause) {
        super(cause);
    }

    public GUIValidationException(Object control) {
        super();
        this.control = control;
    }

    public GUIValidationException(Object control, String message, Throwable cause) {
        super(message, cause);
        this.control = control;
    }

    public GUIValidationException(Object control, String message) {
        super(message);
        this.control = control;
    }

    public GUIValidationException(Object control, Throwable cause) {
        super(cause);
        this.control = control;
    }

    @SuppressWarnings("unchecked")
    public <T> T getControl() {
        return (T) control;
    }

    @Override
    public boolean isQuiet() {
        return false;
    }

}
