package net.bodz.swt.c.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import net.bodz.bas.err.CreateException;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.artifact.ArtifactDoc;

public class AboutDialog
        extends SimpleDialog {

    public AboutDialog(Class<?> clazz) {
        this(new Shell(), SWT.APPLICATION_MODAL, clazz);
    }

    public AboutDialog(Shell parent, int style, Class<?> clazz) {
        this(parent, style, Xjdocs.getDefaultProvider().getOrCreateClassDoc(clazz).to(ArtifactDoc.class));
    }

    public AboutDialog(Shell parent, int style, ArtifactDoc doc) {
        super(parent, style, "About " + doc.getLabel());
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
