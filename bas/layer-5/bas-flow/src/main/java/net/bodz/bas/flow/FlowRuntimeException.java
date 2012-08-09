package net.bodz.bas.flow;

public class FlowRuntimeException
        extends AbstractFlowException {

    private static final long serialVersionUID = 1L;

    public FlowRuntimeException() {
        super();
    }

    public FlowRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowRuntimeException(String message) {
        super(message);
    }

    public FlowRuntimeException(Throwable cause) {
        super(cause);
    }

}
