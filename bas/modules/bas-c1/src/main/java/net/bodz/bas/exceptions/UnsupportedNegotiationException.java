package net.bodz.bas.exceptions;

import net.bodz.bas.oo.NegotiationParameter;

public class UnsupportedNegotiationException
        extends NegotiationException {

    private static final long serialVersionUID = 1L;

    public UnsupportedNegotiationException(NegotiationParameter parameter) {
        super(parameter);
    }

}
