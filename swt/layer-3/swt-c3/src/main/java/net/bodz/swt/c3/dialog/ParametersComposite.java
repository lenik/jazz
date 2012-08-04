package net.bodz.swt.c3.dialog;

import net.bodz.bas.traits.ValidateException;

import org.eclipse.swt.widgets.Composite;

public abstract class ParametersComposite
        extends Composite {

    public ParametersComposite(Composite parent, int style) {
        super(parent, style);
    }

    public abstract Object get()
            throws ValidateException;

}
