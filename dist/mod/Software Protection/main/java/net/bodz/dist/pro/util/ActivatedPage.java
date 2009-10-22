package net.bodz.dist.pro.util;

import net.bodz.bas.types.TreePath;
import net.bodz.dist.pro.nls.ProtectNLS;
import net.bodz.swt.gui.pfl.PageException;
import net.bodz.swt.gui.pfl.PageMethod;
import net.bodz.swt.gui.pfl.ServiceContext;
import net.bodz.swt.gui.pfl._Page;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ActivatedPage extends _Page {

    private final ABTSProvider abtsProvider;

    public ActivatedPage(ABTSProvider abtsProvider, PageMethod... methods) {
        if (abtsProvider == null)
            throw new NullPointerException("abtsProvider");
        this.abtsProvider = abtsProvider;
        setMethods(methods);
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return ProtectNLS.getString("ActivatedPage.title"); //$NON-NLS-1$
    }

    @Override
    protected void createContents(Composite parent) {
        parent.setLayout(new GridLayout());

        final Label thankLabel = new Label(parent, SWT.NONE);
        thankLabel.setText(ProtectNLS.getString("ActivatedPage.thank")); //$NON-NLS-1$
    }

    @Override
    public TreePath service(ServiceContext context) throws PageException {
        ActivationByTargetString abts = abtsProvider.getABTS();
        if (abts != null)
            abts.save();
        return null;
    }

}
