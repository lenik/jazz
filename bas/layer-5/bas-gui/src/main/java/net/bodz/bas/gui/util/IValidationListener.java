package net.bodz.bas.gui.util;

import java.util.EventListener;

import net.bodz.bas.tf.std.ValidationException;

public interface IValidationListener
        extends EventListener {

    void validate(ValidationEvent e)
            throws ValidationException;

}
