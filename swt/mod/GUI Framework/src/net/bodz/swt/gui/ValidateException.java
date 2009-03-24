package net.bodz.swt.gui;

import net.bodz.bas.lang.err.CheckException;

import org.eclipse.swt.widgets.Control;

public class ValidateException extends CheckException {

    private static final long serialVersionUID = 4861800631957356772L;

    private Control           control;
    private boolean           silent;

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

    public boolean isSilent() {
        return silent;
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }

}
