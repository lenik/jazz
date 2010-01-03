package net.bodz.bas.closure.eval;

public class EvalException extends Exception {

    private static final long serialVersionUID = -1782002955385232081L;

    public EvalException() {
        super();
    }

    public EvalException(String message, Throwable cause) {
        super(message, cause);
    }

    public EvalException(String message) {
        super(message);
    }

    public EvalException(Throwable cause) {
        super(cause);
    }

}
