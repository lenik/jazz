package net.bodz.bas.rtx;

import net.bodz.bas.rtx.INegotiation.IParameter;

public class MandatoryException
        extends NegotiationException {

    private static final long serialVersionUID = 1L;

    public MandatoryException(IParameter parameter) {
        super(parameter);
    }

}
