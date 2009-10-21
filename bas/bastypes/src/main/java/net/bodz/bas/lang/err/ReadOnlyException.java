package net.bodz.bas.lang.err;

public class ReadOnlyException extends UnsupportedOperationException {

    private static final long serialVersionUID = -7017976133370303436L;

    public ReadOnlyException() {
        super();
    }

    public ReadOnlyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadOnlyException(String message) {
        super(message);
    }

    public ReadOnlyException(Throwable cause) {
        super(cause);
    }

}
