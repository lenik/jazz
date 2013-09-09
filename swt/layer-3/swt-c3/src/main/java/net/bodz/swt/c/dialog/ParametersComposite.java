package net.bodz.swt.c.dialog;

import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.typer.std.ValidationException;

public abstract class ParametersComposite
        extends Composite {

    public ParametersComposite(Composite parent, int style) {
        super(parent, style);
    }

    public abstract Object get()
            throws ValidationException;

}
