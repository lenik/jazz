package net.bodz.dist.pro.util;

import net.bodz.dist.pro.nls.ProtectNLS;
import net.bodz.swt.gui.pfl.PageComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ActivatedPage extends PageComposite {

    public ActivatedPage(Composite parent, int style) {
        super(parent, style);
        setLayout(new GridLayout());

        final Label thankLabel = new Label(this, SWT.NONE);
        thankLabel.setText(ProtectNLS.getString("ActivatedPage.thank")); //$NON-NLS-1$
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return ProtectNLS.getString("ActivatedPage.title"); //$NON-NLS-1$
    }

}
