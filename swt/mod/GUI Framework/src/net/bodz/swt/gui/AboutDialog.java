package net.bodz.swt.gui;

import net.bodz.bas.a.A_bas;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.swt.dialogs.SimpleDialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * @test {@link AboutDialogTest}
 */
public class AboutDialog extends SimpleDialog {

    public AboutDialog(Class<?> clazz) {
        this(new Shell(), SWT.APPLICATION_MODAL, clazz);
    }

    public AboutDialog(Shell parent, int style, Class<?> clazz) {
        super(parent, style, "About " + A_bas.getDisplayName(clazz));
    }

    @Override
    protected void createButtons(Composite parent) throws CreateException {
        addOKButton(parent).setSelection(true);
    }

    @Override
    public Object open() {
        return super.open(false);
    }

}
