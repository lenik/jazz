package net.bodz.dist.ins.builtins;

import net.bodz.bas.sys.SystemProperties;
import net.bodz.dist.ins.ConfigPage;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.nls.PackNLS;
import net.bodz.swt.controls.util.Playback;
import net.bodz.swt.layouts.BorderLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class LicensePage extends ConfigPage {

    private ISession session;

    private Text     text;

    public LicensePage(ISession session, Composite parent, int style) {
        super(parent, style);
        this.session = session;
        setLayout(new BorderLayout(0, 0));

        final Composite composite = new Composite(this, SWT.NONE);
        final RowLayout rowLayout = new RowLayout();
        rowLayout.justify = true;
        composite.setLayout(rowLayout);
        composite.setLayoutData(BorderLayout.SOUTH);

        final Button agreeButton = new Button(composite, SWT.RADIO);
        agreeButton.setText(PackNLS.getString("LicensePage.agree")); //$NON-NLS-1$
        agreeButton.addSelectionListener(new SetState("next")); //$NON-NLS-1$

        final Button disagreeButton = new Button(composite, SWT.RADIO);
        disagreeButton.setText(PackNLS.getString("LicensePage.disagree")); //$NON-NLS-1$
        disagreeButton.addSelectionListener(new SetState("cancel")); //$NON-NLS-1$

        if (SystemProperties.isDevelopMode())
            Playback.click(agreeButton);
        else
            disagreeButton.setSelection(true);

        text = new Text(this, SWT.V_SCROLL | SWT.READ_ONLY | SWT.MULTI | SWT.BORDER);
        text.setLayoutData(BorderLayout.CENTER);
    }

    @Override
    public void enter(String prev, int reason) {
        String license = session.getProject().getLicense();
        text.setText(license);
    }

    @Override
    protected Object getInitialState() {
        return "cancel"; //$NON-NLS-1$
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return PackNLS.getString("LicensePage.title"); //$NON-NLS-1$
    }

}
