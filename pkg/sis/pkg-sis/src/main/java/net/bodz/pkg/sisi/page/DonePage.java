package net.bodz.pkg.sisi.page;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import net.bodz.bas.t.pojo.PathEntries;
import net.bodz.pkg.sis.ISisProject;
import net.bodz.swt.c.pageflow.PageException;
import net.bodz.swt.c.pageflow.PageMethod;
import net.bodz.swt.c.pageflow.ServiceContext;

class DonePage
        extends SisiPage {

    private ISisProject project;

    public DonePage(final ISisProject project) {
        this.project = project;
        addMethod(new PageMethod("quit", "quit"));
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return tr._("Done");
    }

    @Override
    protected void createContents(Composite holder) {
        holder.setLayout(new GridLayout());

        final Label thsInstallationProcessLabel = new Label(holder, SWT.WRAP);
        thsInstallationProcessLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        thsInstallationProcessLabel.setText(tr
                ._("Ths installation process is completed, you may choose to reboot the computer. "));

        final Button rebootCheck = new Button(holder, SWT.CHECK);
        rebootCheck.setText(tr._("Reboot the computer"));
        rebootCheck.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean reboot = rebootCheck.getSelection();
                project.setRebootRequired(reboot);
                setMethods(new PageMethod(reboot ? "reboot" : "quit"));
            }
        });
    }

    @Override
    public PathEntries service(ServiceContext context)
            throws PageException {
        setMethods(new PageMethod(project.isRebootRequired() ? "reboot" : "quit"));
        return null;
    }

}
