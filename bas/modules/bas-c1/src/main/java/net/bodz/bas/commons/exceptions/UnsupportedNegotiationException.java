package net.bodz.bas.commons.exceptions;

import net.bodz.bas.commons.NegotiationParameter;

public class UnsupportedNegotiationException
        extends NegotiationException {

    private static final long serialVersionUID = 1L;

    public UnsupportedNegotiationException(NegotiationParameter parameter) {
        super(parameter);
    }

}
