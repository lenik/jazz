package net.bodz.bas.mf.std;

import net.bodz.bas.rtx.NoOptions;

public abstract class AbstractValidator<T>
        implements IValidator<T> {

    @Override
    public void validate(T object)
            throws ValidationException {
        validate(object, NoOptions.getInstance());
    }

}
