package net.bodz.bas.repr.path;

public class ServiceTargetException
        extends PathDispatchException {

    private static final long serialVersionUID = 1L;

    public ServiceTargetException() {
        super();
    }

    public ServiceTargetException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceTargetException(String message) {
        super(message);
    }

    public ServiceTargetException(Throwable cause) {
        super(cause);
    }

}
