package net.bodz.bas.err;

public class PackageError
        extends Error {

    private static final long serialVersionUID = 1L;

    public PackageError() {
        super();
    }

    public PackageError(String message, Throwable cause) {
        super(message, cause);
    }

    public PackageError(String message) {
        super(message);
    }

    public PackageError(Throwable cause) {
        super(cause);
    }

}
