package net.bodz.pkg.sis.c;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import net.bodz.bas.c.system.SystemProperties;
import net.bodz.bas.t.pojo.PathEntries;
import net.bodz.pkg.sis.ISisComponent;
import net.bodz.pkg.sisi.page.ConfigPage;
import net.bodz.swt.c.layout.BorderLayout;
import net.bodz.swt.c.pageflow.IPage;
import net.bodz.swt.c.pageflow.PageException;
import net.bodz.swt.c.pageflow.PageMethod;
import net.bodz.swt.c.pageflow.ServiceContext;
import net.bodz.swt.widgets.util.Playback;

public class LicenseAgreement
        extends ConfigComponent {

    private static final long serialVersionUID = 1L;

    public LicenseAgreement(ISisComponent parent) {
        super(parent, null);
    }

    @Override
    public ConfigPage createConfigPage() {
        return new LicenseAgreementPage(this);
    }

}

class LicenseAgreementPage
        extends ConfigPage {

    private Text text;
    private Button agreeButton;
    private Button disagreeButton;

    public LicenseAgreementPage(ISisComponent owner) {
        super(owner);
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return nls.tr("License");
    }

    @Override
    protected void createContents(Composite holder) {
        holder.setLayout(new BorderLayout(0, 0));

        final Composite composite = new Composite(holder, SWT.NONE);
        final RowLayout rowLayout = new RowLayout();
        rowLayout.justify = true;
        composite.setLayout(rowLayout);
        composite.setLayoutData(BorderLayout.SOUTH);

        SelectionAdapter refreshMethods = new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                propertyChangeSupport.firePropertyChange(IPage.PROP_METHODS, null, null);
            }
        };

        agreeButton = new Button(composite, SWT.RADIO);
        agreeButton.setText(nls.tr("&Agree"));
        agreeButton.addSelectionListener(refreshMethods);

        disagreeButton = new Button(composite, SWT.RADIO);
        disagreeButton.setText(nls.tr("&Disagree"));
        disagreeButton.addSelectionListener(refreshMethods);

        if (SystemProperties.isDevelopMode())
            Playback.click(agreeButton);
        else
            disagreeButton.setSelection(true);

        text = new Text(holder, SWT.V_SCROLL | SWT.READ_ONLY | SWT.MULTI | SWT.BORDER);
        text.setLayoutData(BorderLayout.CENTER);
    }

    @Override
    public PathEntries service(ServiceContext context)
            throws PageException {
        String license = getProject().getLicense();
        text.setText(license);
        return null;
    }

    @Override
    protected PageMethod getNextMethod() {
        boolean agreed = agreeButton.getSelection();
        if (agreed)
            return super.getNextMethod();
        else
            return new PageMethod("license-disagree", "cancel");
    }

}
