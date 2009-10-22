package net.bodz.inplas.kid;

public class CheckException extends Exception {

    private static final long serialVersionUID = 6943452216568409463L;

    public CheckException() {
        super();
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

}
