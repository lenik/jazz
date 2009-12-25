package net.bodz.bas.commons.exceptions;

import net.bodz.bas.commons.NegotiationParameter;

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
