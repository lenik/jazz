package net.bodz.bas.lang.negotiation;

import net.bodz.bas.lang.negotiation.INegotiation.Parameter;

public interface INegotiable {

    /**
     * Negotiate the entire negotiation.
     * 
     * @throws NegotiationException
     *             If the callee don't accept the negotiation, for example, an important parameter
     *             isn't supported by the callee.
     */
    void negotiate(INegotiation negotiation)
            throws NegotiationException;

    /**
     * Process the negotiation parameters.
     * <p>
     * If an important parameter is ignored here, exception may be raised, or just return
     * <code>false</code> value.
     * 
     * @return <code>true</code> If the <code>parameter</code> is taken into account,
     *         <code>false</code> if the <code>parameter</code> is ignored.
     */
    boolean negotiate(Parameter parameter)
            throws NegotiationException;

}
