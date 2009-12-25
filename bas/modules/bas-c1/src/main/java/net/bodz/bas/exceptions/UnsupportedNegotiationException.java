package net.bodz.bas.exceptions;

import net.bodz.bas.lang.NegotiationException;
import net.bodz.bas.lang.NegotiationParameter;

public class UnsupportedNegotiationException
        extends NegotiationException {

    private static final long serialVersionUID = 1L;

    public UnsupportedNegotiationException(NegotiationParameter parameter) {
        super(parameter);
    }

}
