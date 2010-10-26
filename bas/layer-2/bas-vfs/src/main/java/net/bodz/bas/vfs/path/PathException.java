package net.bodz.bas.vfs.path;

import java.io.IOException;

public class PathException
        extends IOException {

    private static final long serialVersionUID = 1L;

    public PathException() {
        super();
    }

    public PathException(String message, Throwable cause) {
        super(message, cause);
    }

    public PathException(String message) {
        super(message);
    }

    public PathException(Throwable cause) {
        super(cause);
    }

}
