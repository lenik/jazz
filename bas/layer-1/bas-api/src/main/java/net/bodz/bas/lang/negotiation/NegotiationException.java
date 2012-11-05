package net.bodz.bas.lang.negotiation;

import net.bodz.bas.lang.negotiation.INegotiation.IParameter;

public class NegotiationException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final IParameter parameter;

    public NegotiationException(IParameter parameter) {
        super();
        if (parameter == null)
            throw new NullPointerException("parameter");
        this.parameter = parameter;
    }

    public NegotiationException(IParameter parameter, String message, Throwable cause) {
        super(message, cause);
        if (parameter == null)
            throw new NullPointerException("parameter");
        this.parameter = parameter;
    }

    public NegotiationException(IParameter parameter, String message) {
        super(message);
        if (parameter == null)
            throw new NullPointerException("parameter");
        this.parameter = parameter;
    }

    public NegotiationException(IParameter parameter, Throwable cause) {
        super(cause);
        if (parameter == null)
            throw new NullPointerException("parameter");
        this.parameter = parameter;
    }

    public IParameter getNegotiationParameter() {
        return parameter;
    }

}
