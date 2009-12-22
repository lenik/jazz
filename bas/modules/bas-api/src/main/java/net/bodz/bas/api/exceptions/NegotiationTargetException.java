package net.bodz.bas.api.exceptions;

import net.bodz.bas.api.types.NegotiationParameter;

public class NegotiationTargetException
        extends NegotiationException {

    private static final long serialVersionUID = 1L;

    private final Throwable target;

    public NegotiationTargetException(NegotiationParameter parameter, Throwable targetException) {
        super(parameter, targetException);
        this.target = targetException;
    }

    public Throwable getTargetException() {
        return target;
    }

}
