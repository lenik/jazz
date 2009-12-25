package net.bodz.bas.api.exceptions;

import net.bodz.bas.api.types.NegotiationParameter;

public class NegotiationException
        extends Exception {

    private static final long serialVersionUID = 1L;

    private final NegotiationParameter parameter;

    public NegotiationException(NegotiationParameter parameter) {
        super();
        if (parameter == null)
            throw new NullPointerException("parameter");
        this.parameter = parameter;
    }

    public NegotiationException(NegotiationParameter parameter, String message, Throwable cause) {
        super(message, cause);
        if (parameter == null)
            throw new NullPointerException("parameter");
        this.parameter = parameter;
    }

    public NegotiationException(NegotiationParameter parameter, String message) {
        super(message);
        if (parameter == null)
            throw new NullPointerException("parameter");
        this.parameter = parameter;
    }

    public NegotiationException(NegotiationParameter parameter, Throwable cause) {
        super(cause);
        if (parameter == null)
            throw new NullPointerException("parameter");
        this.parameter = parameter;
    }

    public NegotiationParameter getNegotiationParameter() {
        return parameter;
    }

}
