package net.bodz.bas.flow;

public class FlowUsageException
        extends AbstractFlowException {

    private static final long serialVersionUID = 1L;

    public FlowUsageException() {
        super();
    }

    public FlowUsageException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowUsageException(String message) {
        super(message);
    }

    public FlowUsageException(Throwable cause) {
        super(cause);
    }

}
