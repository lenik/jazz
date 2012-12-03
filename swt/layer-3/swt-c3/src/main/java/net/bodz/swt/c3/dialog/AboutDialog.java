package net.bodz.swt.c3.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import net.bodz.bas.err.CreateException;
import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model1.ArtifactDoc;

public class AboutDialog
        extends SimpleDialog {

    public AboutDialog(Class<?> clazz) {
        this(new Shell(), SWT.APPLICATION_MODAL, clazz);
    }

    public AboutDialog(Shell parent, int style, Class<?> clazz) {
        this(parent, style, ClassDocs.loadFromResource(clazz).as(ArtifactDoc.class));
    }

    public AboutDialog(Shell parent, int style, ArtifactDoc doc) {
        super(parent, style, "About " + doc.getDisplayName());
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
