package net.bodz.bas.lang.negotiation;

import net.bodz.bas.lang.negotiation.INegotiation.Parameter;

public class NegotiationException
        extends Exception {

    private static final long serialVersionUID = 1L;

    private final Parameter parameter;

    public NegotiationException(Parameter parameter) {
        super();
        if (parameter == null)
            throw new NullPointerException("parameter");
        this.parameter = parameter;
    }

    public NegotiationException(Parameter parameter, String message, Throwable cause) {
        super(message, cause);
        if (parameter == null)
            throw new NullPointerException("parameter");
        this.parameter = parameter;
    }

    public NegotiationException(Parameter parameter, String message) {
        super(message);
        if (parameter == null)
            throw new NullPointerException("parameter");
        this.parameter = parameter;
    }

    public NegotiationException(Parameter parameter, Throwable cause) {
        super(cause);
        if (parameter == null)
            throw new NullPointerException("parameter");
        this.parameter = parameter;
    }

    public Parameter getNegotiationParameter() {
        return parameter;
    }

}
