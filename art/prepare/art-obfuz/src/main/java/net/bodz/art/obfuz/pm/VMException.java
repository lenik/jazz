package net.bodz.art.obfuz.pm;

public class VMException
        extends Exception {

    private static final long serialVersionUID = -7716074928284653648L;

    public VMException() {
        super();
    }

    public VMException(String message, Throwable cause) {
        super(message, cause);
    }

    public VMException(String message) {
        super(message);
    }

    public VMException(Throwable cause) {
        super(cause);
    }

}
