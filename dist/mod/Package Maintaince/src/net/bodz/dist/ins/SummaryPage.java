package net.bodz.dist.ins;

import net.bodz.bas.io.CharOuts.BCharOut;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

class SummaryPage extends ConfigPage {

    private ISession session;

    private Text     text;

    public SummaryPage(ISession session, Composite parent, int style) {
        super(parent, style);
        setLayout(new GridLayout());
        this.session = session;

        final Label sessionConfigurationLabel = new Label(this, SWT.NONE);
        sessionConfigurationLabel.setText("Session Configuration: ");

        text = new Text(this, SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY | SWT.MULTI | SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return "Summary";
    }

    @Override
    public void enter(String prev, int reason) {
        BCharOut buf = new BCharOut();
        session.dump(buf);
        String dump = buf.toString();
        text.setText(dump);
    }

}
