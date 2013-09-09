package net.bodz.redist.installer;

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
import net.bodz.swt.c.pageflow.AbstractPage;
import net.bodz.swt.c.pageflow.PageException;
import net.bodz.swt.c.pageflow.PageMethod;
import net.bodz.swt.c.pageflow.ServiceContext;

class DonePage
        extends AbstractPage {

    private ISession session;

    public DonePage(final ISession session) {
        this.session = session;
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
        thsInstallationProcessLabel.setText(tr._("Ths installation process is completed, you may choose to reboot the computer. "));

        final Button rebootCheck = new Button(holder, SWT.CHECK);
        rebootCheck.setText(tr._("Reboot the computer"));
        rebootCheck.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean reboot = rebootCheck.getSelection();
                if (reboot)
                    session.getFlags().set(ISession.REBOOT);
                else
                    session.getFlags().clear(ISession.REBOOT);
                setMethods(new PageMethod(reboot ? "reboot" : "quit"));
            }
        });
    }

    @Override
    public PathEntries service(ServiceContext context)
            throws PageException {
        boolean reboot = session.getFlags().isSet(ISession.REBOOT);
        setMethods(new PageMethod(reboot ? "reboot" : "quit"));
        return null;
    }

}
