package net.bodz.bas.scanner.ep1007;

public class Ep1007Exception
        extends Exception {

    private static final long serialVersionUID = 1L;

    int error;
    RxErrorCode errorCode;

    public Ep1007Exception(String message) {
        super(message);
        error = -1;
    }

    public Ep1007Exception(int error) {
        super();
        setError(error);
    }

    public Ep1007Exception(int error, String message, Throwable cause) {
        super(message, cause);
        setError(error);
    }

    public Ep1007Exception(int error, String message) {
        super(message);
        setError(error);
    }

    public Ep1007Exception(int error, Throwable cause) {
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
