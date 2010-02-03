package net.bodz.bas.potato;

public class NoSuchPotatoMethodException
        extends PotatoException {

    private static final long serialVersionUID = 1L;

    public NoSuchPotatoMethodException() {
        super();
    }

    public NoSuchPotatoMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchPotatoMethodException(String message) {
        super(message);
    }

    public NoSuchPotatoMethodException(Throwable cause) {
        super(cause);
    }

}
