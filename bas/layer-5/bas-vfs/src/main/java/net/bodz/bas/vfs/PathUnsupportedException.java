package net.bodz.bas.vfs;

public class PathUnsupportedException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public PathUnsupportedException() {
    }

    public PathUnsupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PathUnsupportedException(String message) {
        super(message);
    }

    public PathUnsupportedException(Throwable cause) {
        super(cause);
    }

}