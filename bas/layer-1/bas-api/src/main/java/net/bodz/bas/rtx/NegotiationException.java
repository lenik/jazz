package net.bodz.bas.rtx;

import net.bodz.bas.rtx.INegotiation.IParameter;

public class NegotiationException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final IParameter parameter;

    public NegotiationException(IParameter parameter) {
        super("Parameter " + parameter.getId());
        this.parameter = parameter;
    }

    public NegotiationException(IParameter parameter, String message, Throwable cause) {
        super("Parameter " + parameter.getId() + ": " + message, cause);
        this.parameter = parameter;
    }

    public NegotiationException(IParameter parameter, String message) {
        super("Parameter " + parameter.getId() + ": " + message);
        this.parameter = parameter;
    }

    public NegotiationException(IParameter parameter, Throwable cause) {
        super("Parameter " + parameter.getId(), cause);
        this.parameter = parameter;
    }

    public IParameter getNegotiationParameter() {
        return parameter;
    }

}
