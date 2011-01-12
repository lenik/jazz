package net.bodz.bas.vfs.path;

import net.bodz.bas.vfs.PathResolveException;

public class BadPathException
        extends PathResolveException {

    private static final long serialVersionUID = 1L;

    public BadPathException(String path) {
        super(path);
    }

    public BadPathException(String message, String path) {
        super(message + ": " + path);
    }

    public BadPathException(String path, Throwable cause) {
        super(path, cause);
    }

    public BadPathException(String message, String path, Throwable cause) {
        super(message + ": " + path, cause);
    }

}
