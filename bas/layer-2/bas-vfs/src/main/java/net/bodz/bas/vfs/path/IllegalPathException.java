package net.bodz.bas.vfs.path;

public class IllegalPathException
        extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public IllegalPathException() {
        super();
    }

    public IllegalPathException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalPathException(String s) {
        super(s);
    }

    public IllegalPathException(Throwable cause) {
        super(cause);
    }

}
