package net.bodz.dist.ins;

import net.bodz.dist.nls.PackNLS;
import net.bodz.swt.gui.pfl.PageComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @test DonePageTest
 */
class DonePage extends PageComposite {

    private ISession session;

    public DonePage(final ISession session, Composite parent, int style) {
        super(parent, style);
        this.session = session;
        setLayout(new GridLayout());

        final Label thsInstallationProcessLabel = new Label(this, SWT.WRAP);
        thsInstallationProcessLabel.setLayoutData(new GridData(SWT.FILL,
                SWT.CENTER, true, false));
        thsInstallationProcessLabel
                .setText("Ths installation process is completed, you may choose to reboot the computer. ");

        final Button rebootCheck = new Button(this, SWT.CHECK);
        rebootCheck.setText(PackNLS.getString("DonePage.reboot")); //$NON-NLS-1$
        rebootCheck.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean reboot = rebootCheck.getSelection();
                if (reboot) {
                    session.getFlags().set(ISession.REBOOT);
                    setExitState("reboot"); //$NON-NLS-1$
                } else {
                    session.getFlags().clear(ISession.REBOOT);
                    setExitState("quit"); //$NON-NLS-1$
                }
            }
        });
    }

    @Override
    public void enter(String prev, int reason) {
        boolean reboot = session.getFlags().isSet(ISession.REBOOT);
        setExitState(reboot ? "reboot" : "quit"); //$NON-NLS-1$ //$NON-NLS-2$
    }

}
