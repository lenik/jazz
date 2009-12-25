package net.bodz.bas.api.exceptions;

import java.io.IOException;

public class DecodeException extends IOException {

    private static final long serialVersionUID = 1115423726136046853L;

    public DecodeException() {
        super();
    }

    public DecodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DecodeException(String message) {
        super(message);
    }

    public DecodeException(Throwable cause) {
        super(cause);
    }

}
