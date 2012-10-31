package net.bodz.bas.vfs;

public class FileResolveException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileResolveException() {
        super();
    }

    public FileResolveException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileResolveException(String message) {
        super(message);
    }

    public FileResolveException(Throwable cause) {
        super(cause);
    }

}
