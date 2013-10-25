package net.bodz.pkg.sis;

public class SisException
        extends Exception {

    private static final long serialVersionUID = 5056999498893599794L;

    public SisException() {
        super();
    }

    public SisException(String message, Throwable cause) {
        super(message, cause);
    }

    public SisException(String message) {
        super(message);
    }

    public SisException(Throwable cause) {
        super(cause);
    }

}
