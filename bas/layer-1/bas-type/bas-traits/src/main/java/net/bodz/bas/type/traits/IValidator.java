package net.bodz.bas.type.traits;

import net.bodz.bas.lang.INegotiation;
import net.bodz.bas.lang.NegotiationException;

public interface IValidator<T> {

    void validate(T o)
            throws ValidateException;

    void validate(T o, INegotiation negotiation)
            throws ValidateException, NegotiationException;

}
