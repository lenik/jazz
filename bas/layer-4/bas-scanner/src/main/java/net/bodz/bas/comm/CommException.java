package net.bodz.bas.comm;

import java.io.IOException;

public class CommException
        extends IOException {

    private static final long serialVersionUID = 1L;

    public CommException() {
        super();
    }

    public CommException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommException(String message) {
        super(message);
    }

    public CommException(Throwable cause) {
        super(cause);
    }

}
