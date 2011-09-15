package net.bodz.bas.lang.negotiation;

public interface INegotiable {

    /**
     * Negotiate the entire negotiation.
     * 
     * @throws NegotiationException
     *             If the callee don't accept the negotiation, for example, a mandatory parameter
     *             isn't supported by callee.
     */
    void negotiate(INegotiation negotiation)
            throws NegotiationException;

    /**
     * Process the negotiation parameters.
     * <p>
     * If mandatory parameter is not processed, exception may be raised.
     * 
     * @return <code>true</code> If the <code>parameter</code> is taken into account.
     */
    boolean negotiate(NegotiationParameter parameter)
            throws NegotiationException;

}
