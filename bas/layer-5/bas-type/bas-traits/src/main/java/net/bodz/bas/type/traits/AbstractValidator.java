package net.bodz.bas.type.traits;

import net.bodz.bas.lang.INegotiation;
import net.bodz.bas.lang.NegotiationException;

public abstract class AbstractValidator<T>
        implements IValidator<T> {

    @Override
    public void validate(T o, INegotiation negotiation)
            throws ValidateException, NegotiationException {
        if (negotiation != null)
            negotiation.bypass();
        validate(o);
    }

}
