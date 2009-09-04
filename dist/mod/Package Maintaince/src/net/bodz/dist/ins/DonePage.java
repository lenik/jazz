package net.bodz.dist.ins;

import net.bodz.bas.types.TreePath;
import net.bodz.dist.ins.nls.PackNLS;
import net.bodz.swt.gui.pfl.PageMethod;
import net.bodz.swt.gui.pfl.PageException;
import net.bodz.swt.gui.pfl.ServiceContext;
import net.bodz.swt.gui.pfl._Page;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @test {@link DonePageTest}
 */
class DonePage extends _Page {

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
        return PackNLS.getString("DonePage.done"); //$NON-NLS-1$
    }

    @Override
    protected void createContents(Composite holder) {
        holder.setLayout(new GridLayout());

        final Label thsInstallationProcessLabel = new Label(holder, SWT.WRAP);
        thsInstallationProcessLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        thsInstallationProcessLabel.setText(PackNLS.getString("DonePage.installCompleted")); //$NON-NLS-1$

        final Button rebootCheck = new Button(holder, SWT.CHECK);
        rebootCheck.setText(PackNLS.getString("DonePage.reboot")); //$NON-NLS-1$
        rebootCheck.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean reboot = rebootCheck.getSelection();
                if (reboot)
                    session.getFlags().set(ISession.REBOOT);
                else
                    session.getFlags().clear(ISession.REBOOT);
                setMethods(new PageMethod(reboot ? "reboot" : "quit")); //$NON-NLS-1$
            }
        });
    }

    @Override
    public TreePath service(ServiceContext context) throws PageException {
        boolean reboot = session.getFlags().isSet(ISession.REBOOT);
        setMethods(new PageMethod(reboot ? "reboot" : "quit")); //$NON-NLS-1$
        return null;
    }

}
