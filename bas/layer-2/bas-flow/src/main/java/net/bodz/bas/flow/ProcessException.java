package net.bodz.bas.flow;

public class ProcessException
        extends BiosException {

    private static final long serialVersionUID = -6429307096781713836L;

    public ProcessException() {
        super();
    }

    public ProcessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessException(String message) {
        super(message);
    }

    public ProcessException(Throwable cause) {
        super(cause);
    }

}
