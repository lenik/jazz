package net.bodz.dist.ins;

import net.bodz.swt.gui.pfl.PageComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @TestBy DonePageTest
 */
public class DonePage extends PageComposite {

    private ISession session;

    public DonePage(final ISession session, Composite parent, int style) {
        super(parent, style);
        this.session = session;
        setLayout(new GridLayout());

        final Button rebootCheck = new Button(this, SWT.CHECK);
        rebootCheck.setText("Reboot the computer");
        rebootCheck.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean reboot = rebootCheck.getSelection();
                if (reboot) {
                    session.getFlags().set(ISession.REBOOT);
                    setExitState("reboot");
                } else {
                    session.getFlags().clear(ISession.REBOOT);
                    setExitState("quit");
                }
            }
        });
    }

    @Override
    public void enter(String prev) {
        boolean reboot = session.getFlags().isSet(ISession.REBOOT);
        setExitState(reboot ? "reboot" : "quit");
    }

}
