package net.bodz.bas.typer.std;

public class ClassifyException
        extends TypeSystemException {

    private static final long serialVersionUID = 1L;

    public ClassifyException() {
        super();
    }

    public ClassifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassifyException(String message) {
        super(message);
    }

    public ClassifyException(Throwable cause) {
        super(cause);
    }

}
