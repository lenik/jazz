package net.bodz.swt.viz;

import java.util.EventListener;

import net.bodz.bas.gui.err.GUIValidationException;

public interface IValidationListener
        extends EventListener {

    void validate(ValidateEvent e)
            throws GUIValidationException;

}
