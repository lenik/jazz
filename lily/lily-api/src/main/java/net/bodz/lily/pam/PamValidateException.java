package net.bodz.lily.pam;

public class PamValidateException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public PamValidateException() {
        super();
    }

    public PamValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public PamValidateException(String message) {
        super(message);
    }

    public PamValidateException(Throwable cause) {
        super(cause);
    }

}
