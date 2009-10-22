package net.bodz.dist.ins.builtins;

import net.bodz.bas.sys.SystemProperties;
import net.bodz.bas.types.TreePath;
import net.bodz.dist.ins.Component;
import net.bodz.dist.ins.ConfigPage;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.nls.PackNLS;
import net.bodz.swt.gui.pfl.Page;
import net.bodz.swt.gui.pfl.PageException;
import net.bodz.swt.gui.pfl.PageMethod;
import net.bodz.swt.gui.pfl.ServiceContext;
import net.bodz.swt.layouts.BorderLayout;
import net.bodz.swt.widgets.util.Playback;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class LicensePage extends ConfigPage {

    private Text   text;
    private Button agreeButton;
    private Button disagreeButton;

    public LicensePage(Component owner, ISession session) {
        super(owner, session);
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return PackNLS.getString("LicensePage.title"); //$NON-NLS-1$
    }

    @Override
    protected void createContents(Composite holder) throws PageException {
        holder.setLayout(new BorderLayout(0, 0));

        final Composite composite = new Composite(holder, SWT.NONE);
        final RowLayout rowLayout = new RowLayout();
        rowLayout.justify = true;
        composite.setLayout(rowLayout);
        composite.setLayoutData(BorderLayout.SOUTH);

        SelectionAdapter refreshMethods = new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                propertyChangeSupport.firePropertyChange(Page.PROP_METHODS, null, null);
            }
        };

        agreeButton = new Button(composite, SWT.RADIO);
        agreeButton.setText(PackNLS.getString("LicensePage.agree")); //$NON-NLS-1$
        agreeButton.addSelectionListener(refreshMethods);

        disagreeButton = new Button(composite, SWT.RADIO);
        disagreeButton.setText(PackNLS.getString("LicensePage.disagree")); //$NON-NLS-1$
        disagreeButton.addSelectionListener(refreshMethods);

        if (SystemProperties.isDevelopMode())
            Playback.click(agreeButton);
        else
            disagreeButton.setSelection(true);

        text = new Text(holder, SWT.V_SCROLL | SWT.READ_ONLY | SWT.MULTI | SWT.BORDER);
        text.setLayoutData(BorderLayout.CENTER);
    }

    @Override
    public TreePath service(ServiceContext context) throws PageException {
        String license = session.getProject().getLicense();
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
