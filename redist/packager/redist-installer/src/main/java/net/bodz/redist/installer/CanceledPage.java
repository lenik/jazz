package net.bodz.redist.installer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import net.bodz.swt.c3.pageflow.AbstractPage;
import net.bodz.swt.c3.pageflow.PageMethod;

class CanceledPage
        extends AbstractPage {

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
        return tr._("Canceled");
    }

    @Override
    protected void createContents(Composite holder) {
        holder.setLayout(new GridLayout());
        final Label youHaveCanceledLabel = new Label(holder, SWT.WRAP);
        final GridData gd_youHaveCanceledLabel = new GridData(SWT.LEFT, SWT.CENTER, true, false);
        youHaveCanceledLabel.setLayoutData(gd_youHaveCanceledLabel);
        youHaveCanceledLabel.setText(tr._("You have canceled the installation. "));
    }

}
