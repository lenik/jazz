package net.bodz.bas.traits;

import net.bodz.bas.lang.negotiation.INegotiation;

public interface IValidator<T> {

    int traitIndex = -566399735; // IValidator

    void validate(T object)
            throws ValidationException;

    void validate(T object, INegotiation negotiation)
            throws ValidationException;

}
