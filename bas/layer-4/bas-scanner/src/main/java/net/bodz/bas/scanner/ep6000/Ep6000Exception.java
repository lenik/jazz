package net.bodz.bas.scanner.ep6000;

public class Ep6000Exception
        extends Exception {

    private static final long serialVersionUID = 1L;

    int error;
    RxErrorCode errorCode;

    public Ep6000Exception(int error) {
        super();
        setError(error);
    }

    public Ep6000Exception(int error, String message, Throwable cause) {
        super(message, cause);
        setError(error);
    }

    public Ep6000Exception(int error, String message) {
        super(message);
        setError(error);
    }

    public Ep6000Exception(int error, Throwable cause) {
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
