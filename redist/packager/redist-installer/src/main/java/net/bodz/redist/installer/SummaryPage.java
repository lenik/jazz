package net.bodz.redist.installer;

import static net.bodz.redist.installer.nls.PackNLS.PackNLS;
import net.bodz.bas.collection.tree.TreePath;
import net.bodz.bas.sio.BCharOut;
import net.bodz.swt.c3.pageflow.AbstractPage;
import net.bodz.swt.c3.pageflow.PageException;
import net.bodz.swt.c3.pageflow.PageMethod;
import net.bodz.swt.c3.pageflow.ServiceContext;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

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
        return PackNLS.getString("SummaryPage.summary");
    }

    @Override
    protected void createContents(Composite holder) {
        holder.setLayout(new GridLayout());
        final Label sessionConfigurationLabel = new Label(holder, SWT.NONE);
        sessionConfigurationLabel.setText(PackNLS.getString("SummaryPage.sessionConfig"));

        text = new Text(holder, SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY | SWT.MULTI | SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }

    @Override
    public TreePath service(ServiceContext context)
            throws PageException {
        BCharOut buf = new BCharOut();
        session.dump(buf);
        String dump = buf.toString();
        text.setText(dump);
        return null;
    }

}
