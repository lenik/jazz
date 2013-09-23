package net.bodz.pkg.installer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.t.pojo.PathEntries;
import net.bodz.swt.c.pageflow.AbstractPage;
import net.bodz.swt.c.pageflow.PageException;
import net.bodz.swt.c.pageflow.PageMethod;
import net.bodz.swt.c.pageflow.ServiceContext;

class SummaryPage
        extends AbstractPage {

    private ISession session;

    private Text text;

    public SummaryPage(ISession session) {
        if (session == null)
            throw new NullPointerException("session");
        this.session = session;
        addMethod(new PageMethod(ProgressPage.class));
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return tr._("Summary");
    }

    @Override
    protected void createContents(Composite holder) {
        holder.setLayout(new GridLayout());
        final Label sessionConfigurationLabel = new Label(holder, SWT.NONE);
        sessionConfigurationLabel.setText(tr._("Session Configuration: "));

        text = new Text(holder, SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY | SWT.MULTI | SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }

    @Override
    public PathEntries service(ServiceContext context)
            throws PageException {
        BCharOut buf = new BCharOut();
        session.dump(buf);
        String dump = buf.toString();
        text.setText(dump);
        return null;
    }

}