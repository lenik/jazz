package net.bodz.bas.err;

import java.io.IOException;

public class BadFormatException
        extends IOException {

    private static final long serialVersionUID = 1L;

    public BadFormatException() {
        super();
    }

    public BadFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadFormatException(String message) {
        super(message);
    }

    public BadFormatException(Throwable cause) {
        super(cause);
    }

}
