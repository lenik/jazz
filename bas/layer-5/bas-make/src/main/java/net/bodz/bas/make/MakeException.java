package net.bodz.bas.make;

public class MakeException
        extends Exception {

    private static final long serialVersionUID = 2738275444663327244L;

    public MakeException() {
    }

    public MakeException(String message) {
        super(message);
    }

    public MakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MakeException(Throwable cause) {
        super(cause);
    }

}
