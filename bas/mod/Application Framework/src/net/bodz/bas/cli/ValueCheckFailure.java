package net.bodz.bas.cli;

public class ValueCheckFailure extends Error {

    private static final long serialVersionUID = 1740896623654551464L;

    public ValueCheckFailure() {
        super();
    }

    public ValueCheckFailure(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueCheckFailure(String message) {
        super(message);
    }

    public ValueCheckFailure(Throwable cause) {
        super(cause);
    }

}
