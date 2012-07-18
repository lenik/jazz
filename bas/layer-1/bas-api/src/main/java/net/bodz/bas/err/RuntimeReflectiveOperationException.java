package net.bodz.bas.err;

public class RuntimeReflectiveOperationException
        extends RuntimizedException {

    private static final long serialVersionUID = 1L;

    public RuntimeReflectiveOperationException(String message, ReflectiveOperationException cause) {
        super(message, cause);
    }

    public RuntimeReflectiveOperationException(ReflectiveOperationException cause) {
        super(cause);
    }

}
