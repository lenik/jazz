package net.bodz.bas.api.exceptions;

import net.bodz.bas.api.types.NegotiationParameter;

public class UnsupportedNegotiationException
        extends NegotiationException {

    private static final long serialVersionUID = 1L;

    public UnsupportedNegotiationException(NegotiationParameter parameter) {
        super(parameter);
    }

}
