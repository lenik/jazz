package net.bodz.bas.mf.std;

import net.bodz.bas.rtx.INegotiation;

public interface IValidator<T> {

    int mdaFeatureIndex = -566399735; // IValidator

    void validate(T object)
            throws ValidationException;

    void validate(T object, INegotiation negotiation)
            throws ValidationException;

}
