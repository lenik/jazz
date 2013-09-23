package net.bodz.bas.err;

public class FormatterException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FormatterException() {
        super();
    }

    public FormatterException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormatterException(String message) {
        super(message);
    }

    public FormatterException(Throwable cause) {
        super(cause);
    }

}
