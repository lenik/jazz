package net.bodz.bas.cli;

public class TypeCheckFailure extends Error {

    private static final long serialVersionUID = 1740896623654551464L;

    public TypeCheckFailure() {
        super();
    }

    public TypeCheckFailure(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeCheckFailure(String message) {
        super(message);
    }

    public TypeCheckFailure(Throwable cause) {
        super(cause);
    }

}
