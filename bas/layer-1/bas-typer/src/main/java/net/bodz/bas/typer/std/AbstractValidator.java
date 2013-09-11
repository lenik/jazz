package net.bodz.bas.typer.std;

import net.bodz.bas.rtx.IOptions;

public abstract class AbstractValidator<T>
        implements IValidator<T> {

    @Override
    public void validate(T object)
            throws ValidationException {
        validate(object, IOptions.NULL);
    }

}
