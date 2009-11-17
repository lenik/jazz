package net.bodz.bas.api.exceptions;

import java.io.IOException;

public class EncodeException extends IOException {

    private static final long serialVersionUID = 3819822659799122378L;

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
