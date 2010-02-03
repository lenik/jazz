package net.bodz.bas.potato;

public class PotatoTargetException
        extends PotatoException {

    private static final long serialVersionUID = 1L;

    public PotatoTargetException() {
        super();
    }

    public PotatoTargetException(String message, Throwable cause) {
        super(message, cause);
    }

    public PotatoTargetException(String message) {
        super(message);
    }

    public PotatoTargetException(Throwable cause) {
        super(cause);
    }

}
