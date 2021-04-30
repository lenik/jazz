package net.bodz.pkg.sisi.page;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import net.bodz.bas.t.pojo.PathEntries;
import net.bodz.pkg.sis.ISisProject;
import net.bodz.pkg.sis.util.SisDumper;
import net.bodz.swt.c.pageflow.PageException;
import net.bodz.swt.c.pageflow.PageMethod;
import net.bodz.swt.c.pageflow.ServiceContext;

class SummaryPage
        extends SisiPage {

    private ISisProject project;

    private Text text;

    public SummaryPage(ISisProject project) {
        if (project == null)
            throw new NullPointerException("project");
        this.project = project;
        addMethod(new PageMethod(ProgressPage.class));
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return nls.tr("Summary");
    }

    @Override
    protected void createContents(Composite holder) {
        holder.setLayout(new GridLayout());
        final Label sessionConfigurationLabel = new Label(holder, SWT.NONE);
        sessionConfigurationLabel.setText(nls.tr("Session Configuration: "));

        text = new Text(holder, SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY | SWT.MULTI | SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }

    @Override
    public PathEntries service(ServiceContext context)
            throws PageException {
        String dump = SisDumper.dump(project);
        text.setText(dump);
        return null;
    }

}
