package net.bodz.pkg.sisi.page;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import net.bodz.pkg.sis.ISisProject;
import net.bodz.swt.c.pageflow.PageMethod;

class CanceledPage
        extends SisiPage {

    ISisProject project;

    public CanceledPage(ISisProject project) {
        this.project = project;
        addMethod(new PageMethod("quit", "quit"));
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return nls.tr("Canceled");
    }

    @Override
    protected void createContents(Composite holder) {
        holder.setLayout(new GridLayout());
        final Label youHaveCanceledLabel = new Label(holder, SWT.WRAP);
        final GridData gd_youHaveCanceledLabel = new GridData(SWT.LEFT, SWT.CENTER, true, false);
        youHaveCanceledLabel.setLayoutData(gd_youHaveCanceledLabel);
        youHaveCanceledLabel.setText(nls.tr("You have canceled the installation. "));
    }

}
