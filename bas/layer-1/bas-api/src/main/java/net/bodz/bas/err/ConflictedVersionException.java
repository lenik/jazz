package net.bodz.bas.err;


public class ConflictedVersionException
        extends IllegalUsageException {

    private static final long serialVersionUID = 1L;

    public ConflictedVersionException() {
        super();
    }

    public ConflictedVersionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConflictedVersionException(String message) {
        super(message);
    }

    public ConflictedVersionException(Throwable cause) {
        super(cause);
    }

}
