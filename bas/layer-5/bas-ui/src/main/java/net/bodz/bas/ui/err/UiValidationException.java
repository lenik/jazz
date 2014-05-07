package net.bodz.bas.ui.err;

import net.bodz.bas.c.event.IQuietHint;
import net.bodz.bas.typer.std.ValidationException;

public class UiValidationException
        extends ValidationException
        implements IQuietHint {

    private static final long serialVersionUID = 1L;

    Object control;
    boolean quiet;

    public UiValidationException() {
        super();
    }

    public UiValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UiValidationException(String message) {
        super(message);
    }

    public UiValidationException(Throwable cause) {
        super(cause);
    }

    public UiValidationException(Object control) {
        super();
        this.control = control;
    }

    public UiValidationException(Object control, String message, Throwable cause) {
        super(message, cause);
        this.control = control;
    }

    public UiValidationException(Object control, String message) {
        super(message);
        this.control = control;
    }

    public UiValidationException(Object control, Throwable cause) {
        super(cause);
        this.control = control;
    }

    @SuppressWarnings("unchecked")
    public <T> T getControl() {
        return (T) control;
    }

    @Override
    public boolean isQuiet() {
        return quiet;
    }

}
