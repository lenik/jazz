package net.bodz.bas.vfs.path;

public class BadPathException
        extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public BadPathException(String path) {
        super(path);
    }

    public BadPathException(String message, String path) {
        super(message + ": " + path);
    }

}
