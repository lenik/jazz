package net.bodz.swt.c.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import net.bodz.bas.err.CreateException;

public class CreditDialog
        extends SimpleDialog {

    public CreditDialog() {
        this(new Shell(), SWT.APPLICATION_MODAL);
    }

    public CreditDialog(Shell parent, int style) {
        super(parent, style, "Credits");
    }

    @Override
    protected void createButtons(Composite parent)
            throws CreateException {
        addOKButton(parent).setSelection(true);
    }

    @Override
    public Object open() {
        return super.open(false);
    }

}
