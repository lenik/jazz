package net.bodz.bas.lang.negotiation;

import net.bodz.bas.lang.negotiation.INegotiation.Parameter;

public abstract class AbstractNegotiable
        implements INegotiable {

    @Override
    public void negotiate(INegotiation negotiation)
            throws NegotiationException {
        // Set<Parameter> usedSet = new HashSet<Parameter>();
        for (Parameter param : negotiation) {
            /* boolean used = */negotiate(param);
            // usedSet.add(used);
        }
        // Check if any important parameter unused.
    }

    @Override
    public abstract boolean negotiate(Parameter parameter)
            throws NegotiationException;

}
