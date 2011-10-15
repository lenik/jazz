package net.bodz.bas.traits;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;

public interface IValidator<T> {

    int traitIndex = -566399735; // IValidator

    void validate(T o)
            throws ValidateException;

    void validate(T o, INegotiation negotiation)
            throws ValidateException, NegotiationException;

}
