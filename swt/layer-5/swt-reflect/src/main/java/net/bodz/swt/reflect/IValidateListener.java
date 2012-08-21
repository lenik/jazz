package net.bodz.swt.reflect;

import java.util.EventListener;

import net.bodz.swt.gui.err.ValidateException;

public interface IValidateListener
        extends EventListener {

    void validate(ValidateEvent e)
            throws ValidateException;

}
