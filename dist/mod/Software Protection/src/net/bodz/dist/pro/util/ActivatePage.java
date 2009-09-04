package net.bodz.dist.pro.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.dist.pro.nls.ProtectNLS;
import net.bodz.swt.adapters.ControlAdapters;
import net.bodz.swt.gui.pfl.PageMethod;
import net.bodz.swt.gui.pfl._Page;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;

/**
 * @test {@link ActivatePageTest}
 */
public class ActivatePage extends _Page {

    private ActivationByTargetString abt;

    private Text                     hostidText;
    private Text                     codeText;

    private String                   actionPrefix;

    public ActivatePage(ActivationByTargetString abt, String actionPrefix) {
        if (abt == null)
            throw new NullPointerException("abt"); //$NON-NLS-1$
        if (actionPrefix == null)
            throw new NullPointerException("actionPrefix");
        this.abt = abt;
        this.actionPrefix = actionPrefix;
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return ProtectNLS.getString("ActivatePage.title"); //$NON-NLS-1$
    }

    @Override
    protected void createContents(final Composite holder) {
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        holder.setLayout(gridLayout);

        final Label pleaseEnterRegistrationLabel = new Label(holder, SWT.NONE);
        final GridData gd_pleaseEnterRegistrationLabel = new GridData(SWT.LEFT, SWT.CENTER, false,
                false, 2, 1);
        pleaseEnterRegistrationLabel.setLayoutData(gd_pleaseEnterRegistrationLabel);
        pleaseEnterRegistrationLabel.setText(ProtectNLS.getString("ActivatePage.caption")); //$NON-NLS-1$

        final Label hostIdLabel = new Label(holder, SWT.NONE);
        final GridData gd_hostIdLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false);
        hostIdLabel.setLayoutData(gd_hostIdLabel);
        hostIdLabel.setText(ProtectNLS.getString("ActivatePage.hostId")); //$NON-NLS-1$

        hostidText = new Text(holder, SWT.BORDER | SWT.READ_ONLY);
        final GridData gd_hostidText = new GridData(SWT.FILL, SWT.CENTER, true, false);
        hostidText.setLayoutData(gd_hostidText);
        hostidText.setText(abt.getHostId());

        final Label codeLabel = new Label(holder, SWT.NONE);
        final GridData gd_codeLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false);
        codeLabel.setLayoutData(gd_codeLabel);
        codeLabel.setText(ProtectNLS.getString("ActivatePage.activationCode")); //$NON-NLS-1$

        codeText = new Text(holder, SWT.BORDER);
        final GridData gd_codeText = new GridData(SWT.FILL, SWT.CENTER, true, false);
        codeText.setLayoutData(gd_codeText);
        codeText.setFocus();
        codeText.addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                holder.getDisplay().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        // the methods depend on the decrypted codes.
                        propertyChangeSupport.firePropertyChange(PROP_METHODS, null, null);
                    }
                });
            }
        });

        final Label label = new Label(holder, SWT.HORIZONTAL | SWT.SEPARATOR);
        label.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, true, 2, 1));
        label.setText("Label"); //$NON-NLS-1$

        final Label label1 = new Label(holder, SWT.WRAP);
        final GridData gd_label1 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 2, 1);
        label1.setLayoutData(gd_label1);
        label1.setText(ProtectNLS.getString("ActivatePage.hint1")); //$NON-NLS-1$

        String website = abt.getWebsite();
        final Link link = new Link(holder, SWT.NONE);
        final GridData gd_label2 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 2, 1);
        link.setLayoutData(gd_label2);
        link.setText(String.format(ProtectNLS.getString("ActivatePage.accessWebsite_s"), website)); //$NON-NLS-1$
        if (website != null)
            link.setToolTipText(website);
        ControlAdapters.browseLink(link);
    }

    @Override
    public List<PageMethod> getMethods() {
        String code = codeText.getText();
        abt.setActivateCode(code);
        String s;
        try {
            s = abt.getTargetString();
        } catch (ParseException ex) {
            s = "cancel"; //$NON-NLS-1$
        }
        List<PageMethod> methods = new ArrayList<PageMethod>(1);
        inferMethods(methods, s);
        return methods;
    }

    protected void inferMethods(List<PageMethod> methods, String abts) {
        String action = actionPrefix + abts;
        String label = abts;
        methods.add(new PageMethod(action, label));
    }

}
