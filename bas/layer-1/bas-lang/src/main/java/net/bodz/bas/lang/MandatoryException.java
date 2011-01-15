package net.bodz.bas.lang;

public class MandatoryException
        extends NegotiationException {

    private static final long serialVersionUID = 1L;

    public MandatoryException(NegotiationParameter parameter) {
        super(parameter);
    }

}
