package net.bodz.bas.lang;


public class UnsupportedNegotiationException
        extends NegotiationException {

    private static final long serialVersionUID = 1L;

    public UnsupportedNegotiationException(NegotiationParameter parameter) {
        super(parameter);
    }

}
