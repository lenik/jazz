package net.bodz.bas.io;

public class PrintException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PrintException() {
        super();
    }

    public PrintException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrintException(String message) {
        super(message);
    }

    public PrintException(Throwable cause) {
        super(cause);
    }

}
