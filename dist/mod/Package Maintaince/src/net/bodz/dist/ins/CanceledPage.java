package net.bodz.dist.ins;

import net.bodz.dist.ins.nls.PackNLS;
import net.bodz.swt.gui.pfl.PageMethod;
import net.bodz.swt.gui.pfl._Page;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

class CanceledPage extends _Page {

    ISession session;

    public CanceledPage(ISession session) {
        this.session = session;
        addMethod(new PageMethod("quit", "quit"));
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return PackNLS.getString("CanceledPage.canceled"); //$NON-NLS-1$
    }

    @Override
    protected void createContents(Composite holder) {
        holder.setLayout(new GridLayout());
        final Label youHaveCanceledLabel = new Label(holder, SWT.WRAP);
        final GridData gd_youHaveCanceledLabel = new GridData(SWT.LEFT, SWT.CENTER, true, false);
        youHaveCanceledLabel.setLayoutData(gd_youHaveCanceledLabel);
        youHaveCanceledLabel.setText(PackNLS.getString("CanceledPage.youHaveCanceled")); //$NON-NLS-1$
    }

}
