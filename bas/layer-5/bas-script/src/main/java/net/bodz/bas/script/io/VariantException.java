package net.bodz.bas.script.io;

public class VariantException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public VariantException() {
        super();
    }

    public VariantException(String message, Throwable cause) {
        super(message, cause);
    }

    public VariantException(String message) {
        super(message);
    }

    public VariantException(Throwable cause) {
        super(cause);
    }

}
