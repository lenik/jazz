package net.bodz.swt.gui;

import org.eclipse.swt.widgets.Control;

public class SlientValidationException extends ValidateException {

    private static final long serialVersionUID = -7563355585144284953L;

    public SlientValidationException() {
        super();
    }

    public SlientValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SlientValidationException(String message) {
        super(message);
    }

    public SlientValidationException(Throwable cause) {
        super(cause);
    }

    public SlientValidationException(Control control, String message,
            Throwable cause) {
        super(control, message, cause);
    }

    public SlientValidationException(Control control, String message) {
        super(control, message);
    }

    public SlientValidationException(Control control, Throwable cause) {
        super(control, cause);
    }

    public SlientValidationException(Control control) {
        super(control);
    }

    @Override
    public boolean isSilent() {
        return true;
    }

}
