package net.bodz.bas.lang.negotiation;

public interface INegotiable {

    /**
     * Negotiate the entire negotiation.
     * 
     * @throws NegotiationException
     *             If the callee don't accept the negotiation, for example, an important parameter
     *             isn't supported by the callee.
     */
    void negotiate(INegotiation negotiation);

}
