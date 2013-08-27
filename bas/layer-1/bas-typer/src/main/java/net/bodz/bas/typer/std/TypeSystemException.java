package net.bodz.bas.typer.std;

public class TypeSystemException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public TypeSystemException() {
        super();
    }

    public TypeSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeSystemException(String message) {
        super(message);
    }

    public TypeSystemException(Throwable cause) {
        super(cause);
    }

}
