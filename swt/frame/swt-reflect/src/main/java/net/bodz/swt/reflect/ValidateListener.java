package net.bodz.swt.reflect;

import java.util.EventListener;

import net.bodz.swt.err.ValidateException;

public interface ValidateListener
        extends EventListener {

    void validate(ValidateEvent e)
            throws ValidateException;

}
