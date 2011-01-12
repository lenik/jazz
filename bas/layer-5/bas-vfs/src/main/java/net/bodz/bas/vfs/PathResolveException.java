package net.bodz.bas.vfs;

import java.io.IOException;

public class PathResolveException
        extends IOException {

    private static final long serialVersionUID = 1L;

    public PathResolveException() {
        super();
    }

    public PathResolveException(String message, Throwable cause) {
        super(message, cause);
    }

    public PathResolveException(String message) {
        super(message);
    }

    public PathResolveException(Throwable cause) {
        super(cause);
    }

}
