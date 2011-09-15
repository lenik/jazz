package net.bodz.bas.lang.negotiation;

public abstract class AbstractNegotiable
        implements INegotiable {

    @Override
    public void negotiate(INegotiation negotiation)
            throws NegotiationException {
        for (NegotiationParameter negotiationParameter : negotiation) {
            if (negotiationParameter.isMandatory()) {
                boolean isDone = negotiate(negotiationParameter);
                if (!isDone)
                    throw new MandatoryException(negotiationParameter);
            } else {
                negotiate(negotiationParameter);
            }
        }
    }

}
