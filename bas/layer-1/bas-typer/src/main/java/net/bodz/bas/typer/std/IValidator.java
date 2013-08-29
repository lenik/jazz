package net.bodz.bas.typer.std;

import net.bodz.bas.rtx.IOptions;

public interface IValidator<T>
        extends IStdTyper {

    int typerIndex = -566399735; // IValidator

    void validate(T object)
            throws ValidationException;

    void validate(T object, IOptions options)
            throws ValidationException;

}
