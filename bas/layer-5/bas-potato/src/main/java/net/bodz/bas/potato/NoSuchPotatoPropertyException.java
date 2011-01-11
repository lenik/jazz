package net.bodz.bas.potato;

public class NoSuchPotatoPropertyException
        extends PotatoException {

    private static final long serialVersionUID = 1L;

    public NoSuchPotatoPropertyException() {
        super();
    }

    public NoSuchPotatoPropertyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchPotatoPropertyException(String message) {
        super(message);
    }

    public NoSuchPotatoPropertyException(Throwable cause) {
        super(cause);
    }

}
