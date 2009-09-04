package net.bodz.swt.gui;

import net.bodz.bas.lang.err.CheckException;

import org.eclipse.swt.widgets.Control;

public class ValidateException extends CheckException implements QuietHint {

    private static final long serialVersionUID = 4861800631957356772L;

    private Control           control;

    public ValidateException() {
        super();
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(Throwable cause) {
        super(cause);
    }

    /**
     * Construct a silence validation.
     */
    public ValidateException(Control control) {
        super();
        this.control = control;
    }

    public ValidateException(Control control, String message, Throwable cause) {
        super(message, cause);
        this.control = control;
    }

    public ValidateException(Control control, String message) {
        super(message);
        this.control = control;
    }

    public ValidateException(Control control, Throwable cause) {
        super(cause);
        this.control = control;
    }

    public Control getControl() {
        return control;
    }

    @Override
    public boolean isQuiet() {
        return false;
    }

}
