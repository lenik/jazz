package net.bodz.bas.potato;

public class PotatoException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public PotatoException() {
        super();
    }

    public PotatoException(String message, Throwable cause) {
        super(message, cause);
    }

    public PotatoException(String message) {
        super(message);
    }

    public PotatoException(Throwable cause) {
        super(cause);
    }

}
