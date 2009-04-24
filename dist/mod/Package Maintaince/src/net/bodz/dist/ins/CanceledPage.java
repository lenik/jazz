package net.bodz.dist.ins;

import net.bodz.dist.nls.PackNLS;
import net.bodz.swt.gui.pfl.PageComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

class CanceledPage extends PageComposite {

    ISession session;

    public CanceledPage(ISession session, Composite parent, int style) {
        super(parent, style);
        setLayout(new GridLayout());
        this.session = session;

        final Label youHaveCanceledLabel = new Label(this, SWT.WRAP);
        final GridData gd_youHaveCanceledLabel = new GridData(SWT.LEFT, SWT.CENTER, true, false);
        youHaveCanceledLabel.setLayoutData(gd_youHaveCanceledLabel);
        youHaveCanceledLabel.setText(PackNLS.getString("CanceledPage.youHaveCanceled")); //$NON-NLS-1$
    }

    @Override
    protected Object getInitialState() {
        return "quit"; //$NON-NLS-1$
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return PackNLS.getString("CanceledPage.canceled"); //$NON-NLS-1$
    }

}
