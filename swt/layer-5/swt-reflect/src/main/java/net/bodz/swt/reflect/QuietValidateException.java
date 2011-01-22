package net.bodz.swt.reflect;

import org.eclipse.swt.widgets.Control;

public class QuietValidateException extends ValidateException {

    private static final long serialVersionUID = -7563355585144284953L;

    public QuietValidateException() {
        super();
    }

    public QuietValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuietValidateException(String message) {
        super(message);
    }

    public QuietValidateException(Throwable cause) {
        super(cause);
    }

    public QuietValidateException(Control control, String message, Throwable cause) {
        super(control, message, cause);
    }

    public QuietValidateException(Control control, String message) {
        super(control, message);
    }

    public QuietValidateException(Control control, Throwable cause) {
        super(control, cause);
    }

    public QuietValidateException(Control control) {
        super(control);
    }

    @Override
    public boolean isQuiet() {
        return true;
    }

}
