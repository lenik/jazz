package net.bodz.swt.c3.dialog;

import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.mf.std.ValidationException;

public abstract class ParametersComposite
        extends Composite {

    public ParametersComposite(Composite parent, int style) {
        super(parent, style);
    }

    public abstract Object get()
            throws ValidationException;

}
