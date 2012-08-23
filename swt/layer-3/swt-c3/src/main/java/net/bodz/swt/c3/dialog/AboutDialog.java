package net.bodz.swt.c3.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import net.bodz.bas.err.CreateException;

public class AboutDialog
        extends SimpleDialog {

    public AboutDialog(Class<?> clazz) {
        this(new Shell(), SWT.APPLICATION_MODAL, clazz);
    }

    public AboutDialog(Shell parent, int style, Class<?> clazz) {
        super(parent, style, "About " + A_bas.getDisplayName(clazz));
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