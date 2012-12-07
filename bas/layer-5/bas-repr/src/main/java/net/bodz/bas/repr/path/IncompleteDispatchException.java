package net.bodz.bas.repr.path;

public class IncompleteDispatchException
        extends PathDispatchException {

    private static final long serialVersionUID = 1L;

    public IncompleteDispatchException() {
        super();
    }

    public IncompleteDispatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompleteDispatchException(String message) {
        super(message);
    }

    public IncompleteDispatchException(Throwable cause) {
        super(cause);
    }

}
