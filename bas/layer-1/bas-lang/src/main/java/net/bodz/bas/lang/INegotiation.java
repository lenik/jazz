package net.bodz.bas.lang;

public interface INegotiation
        extends Iterable<NegotiationParameter> {

    int getParameterCount();

    NegotiationParameter getParameter(int index);

    /**
     * The callee doesn't support negotation.
     * 
     * This is also a chance for caller to adjust its default strategy to conform to the
     * non-negotiated session.
     */
    void bypass()
            throws UnsupportedNegotiationException;

}
