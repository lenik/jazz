package net.bodz.bas.mf.std;

import net.bodz.bas.rtx.INegotiation;

public abstract class AbstractValidator<T>
        implements IValidator<T> {

    @Override
    public void validate(T o, INegotiation negotiation)
            throws ValidationException {
        if (negotiation != null)
            negotiation.ignore();
        validate(o);
    }

}
