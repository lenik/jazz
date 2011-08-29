package net.bodz.bas.err;

import java.io.IOException;

public class EncodeException
        extends IOException {

    private static final long serialVersionUID = 1L;

    public EncodeException() {
        super();
    }

    public EncodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncodeException(String message) {
        super(message);
    }

    public EncodeException(Throwable cause) {
        super(cause);
    }

}
