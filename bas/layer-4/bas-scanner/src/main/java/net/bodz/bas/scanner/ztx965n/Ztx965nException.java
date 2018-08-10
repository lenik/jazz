package net.bodz.bas.scanner.ztx965n;

public class Ztx965nException
        extends Exception {

    private static final long serialVersionUID = 1L;

    int error;
    RxErrorCode errorCode;

    public Ztx965nException(int error) {
        super();
        setError(error);
    }

    public Ztx965nException(int error, String message, Throwable cause) {
        super(message, cause);
        setError(error);
    }

    public Ztx965nException(int error, String message) {
        super(message);
        setError(error);
    }

    public Ztx965nException(int error, Throwable cause) {
        super(cause);
        setError(error);
    }

    public RxErrorCode getErrorCode() {
        return errorCode;
    }

    public int getError() {
        return error;
    }

    void setError(int error) {
        this.error = error;
        this.errorCode = RxErrorCode.forCode(error);
    }

}
