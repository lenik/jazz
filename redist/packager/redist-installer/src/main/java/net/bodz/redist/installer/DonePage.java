package net.bodz.redist.installer;

import static net.bodz.redist.installer.nls.PackNLS.PackNLS;
import net.bodz.bas.collection.tree.TreePath;
import net.bodz.swt.c3.pageflow.AbstractPage;
import net.bodz.swt.c3.pageflow.PageException;
import net.bodz.swt.c3.pageflow.PageMethod;
import net.bodz.swt.c3.pageflow.ServiceContext;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

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
        return PackNLS.getString("DonePage.done");
    }

    @Override
    protected void createContents(Composite holder) {
        holder.setLayout(new GridLayout());

        final Label thsInstallationProcessLabel = new Label(holder, SWT.WRAP);
        thsInstallationProcessLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        thsInstallationProcessLabel.setText(PackNLS.getString("DonePage.installCompleted"));

        final Button rebootCheck = new Button(holder, SWT.CHECK);
        rebootCheck.setText(PackNLS.getString("DonePage.reboot"));
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
    public TreePath service(ServiceContext context)
            throws PageException {
        boolean reboot = session.getFlags().isSet(ISession.REBOOT);
        setMethods(new PageMethod(reboot ? "reboot" : "quit"));
        return null;
    }

}
