package net.bodz.bas.aspect.typeinfo;

import net.bodz.bas.api.exceptions.NegotiationException;
import net.bodz.bas.api.types.Negotiation;

public interface Validator<T> {

    void validate(T object)
            throws ValidateException;

    void validate(T object, Negotiation negotiation)
            throws ValidateException, NegotiationException;

}
