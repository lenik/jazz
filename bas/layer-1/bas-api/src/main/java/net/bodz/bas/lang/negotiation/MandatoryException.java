package net.bodz.bas.lang.negotiation;

import net.bodz.bas.lang.negotiation.INegotiation.IParameter;

public class MandatoryException
        extends NegotiationException {

    private static final long serialVersionUID = 1L;

    public MandatoryException(IParameter parameter) {
        super(parameter);
    }

}
