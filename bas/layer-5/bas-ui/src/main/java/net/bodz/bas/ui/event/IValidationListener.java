package net.bodz.bas.ui.event;

import java.util.EventListener;

import net.bodz.bas.typer.std.ValidationException;

public interface IValidationListener
        extends EventListener {

    void validate(ValidationEvent e)
            throws ValidationException;

}
