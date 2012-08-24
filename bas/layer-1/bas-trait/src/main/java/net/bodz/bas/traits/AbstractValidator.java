package net.bodz.bas.traits;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;

public abstract class AbstractValidator<T>
        implements IValidator<T> {

    @Override
    public void validate(T o, INegotiation negotiation)
            throws ValidationException, NegotiationException {
        if (negotiation != null)
            negotiation.ignore();
        validate(o);
    }

}
