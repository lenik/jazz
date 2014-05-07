package net.bodz.bas.ui.err;

/**
 * A helper exception. So you don't have to set quiet=true.
 */
public class QuietValidationException
        extends UiValidationException {

    private static final long serialVersionUID = 1L;

    public QuietValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuietValidationException(String message) {
        super(message);
    }

    public QuietValidationException(Throwable cause) {
        super(cause);
    }

    public QuietValidationException() {
        super();
    }

    public QuietValidationException(Object control, String message, Throwable cause) {
        super(control, message, cause);
    }

    public QuietValidationException(Object control, String message) {
        super(control, message);
    }

    public QuietValidationException(Object control, Throwable cause) {
        super(control, cause);
    }

    public QuietValidationException(Object control) {
        super(control);
    }

    @Override
    public boolean isQuiet() {
        return true;
    }

}
