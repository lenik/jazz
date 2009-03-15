package net.bodz.swt.gui;

import net.bodz.bas.lang.err.CheckException;

import org.eclipse.swt.widgets.Control;

public class ValidateException extends CheckException {

    private static final long serialVersionUID = 4861800631957356772L;

    public Control            control;

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

}
