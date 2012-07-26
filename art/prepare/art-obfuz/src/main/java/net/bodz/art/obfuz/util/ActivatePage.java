package net.bodz.art.obfuz.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.art.obfuz.nls.ProtectNLS;
import net.bodz.bas.err.ParseException;
import net.bodz.swt.adapters.ControlAdapters;
import net.bodz.swt.gui.pfl.AbstractPage;
import net.bodz.swt.gui.pfl.PageMethod;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
public class ActivatePage
        extends AbstractPage {

    private ABTSProvider abtsProvider;

    // private Combo type;
    private Text companyText;
    private Text emailText;
    // private Text expireDate;

    private Text hostIdText;
    private Text codeText;

    private String actionPrefix;

    private Link link;

    public ActivatePage(ABTSProvider abtsProvider, String actionPrefix) {
        if (abtsProvider == null)
            throw new NullPointerException("abt"); //$NON-NLS-1$
        if (actionPrefix == null)
            throw new NullPointerException("actionPrefix");
        this.abtsProvider = abtsProvider;
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

    class RecalcAdapter
            implements ModifyListener {

        @Override
        public void modifyText(ModifyEvent e) {
            updateABTS();
        }

    }

    RecalcAdapter recalcAdapter = new RecalcAdapter();

    @Override
    protected void createContents(final Composite holder) {
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        holder.setLayout(gridLayout);

        final Label welcomeLabel = new Label(holder, SWT.NONE);
        final GridData gd_pleaseEnterRegistrationLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
        welcomeLabel.setLayoutData(gd_pleaseEnterRegistrationLabel);
        welcomeLabel.setText(ProtectNLS.getString("ActivatePage.caption")); //$NON-NLS-1$

        final Label companyLabel = new Label(holder, SWT.NONE);
        final GridData gd_companyLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false);
        companyLabel.setLayoutData(gd_companyLabel);
        companyLabel.setText("Company: "); //$NON-NLS-1$

        companyText = new Text(holder, SWT.BORDER);
        final GridData gd_companyText = new GridData(SWT.FILL, SWT.CENTER, true, false);
        companyText.setLayoutData(gd_companyText);
        companyText.addModifyListener(recalcAdapter);

        final Label emailLabel = new Label(holder, SWT.NONE);
        final GridData gd_emailLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false);
        emailLabel.setLayoutData(gd_emailLabel);
        emailLabel.setText("Email: "); //$NON-NLS-1$

        emailText = new Text(holder, SWT.BORDER);
        final GridData gd_emailText = new GridData(SWT.FILL, SWT.CENTER, true, false);
        emailText.setLayoutData(gd_emailText);
        emailText.addModifyListener(recalcAdapter);

        final Label hostIdLabel = new Label(holder, SWT.NONE);
        final GridData gd_hostIdLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false);
        hostIdLabel.setLayoutData(gd_hostIdLabel);
        hostIdLabel.setText(ProtectNLS.getString("ActivatePage.hostId")); //$NON-NLS-1$

        hostIdText = new Text(holder, SWT.BORDER | SWT.READ_ONLY);
        final GridData gd_hostIdText = new GridData(SWT.FILL, SWT.CENTER, true, false);
        hostIdText.setLayoutData(gd_hostIdText);

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

        link = new Link(holder, SWT.NONE);
        final GridData gd_label2 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 2, 1);
        link.setLayoutData(gd_label2);
        ControlAdapters.openBrowser(link);
    }

    void updateABTS() {
        String hostId = "";
        String website = null;

        ActivationByTargetString abts = abtsProvider.getABTS();
        if (abts != null) {
            hostId = abts.getHostId();
            website = abts.getWebsite();
        }

        hostIdText.setText(hostId);
        if (website == null)
            website = "";
        link.setText(String.format(ProtectNLS.getString("ActivatePage.accessWebsite_s"), website)); //$NON-NLS-1$
        link.setToolTipText(website);
    }

    @Override
    public List<PageMethod> getMethods() {
        String s = "cancel";
        ActivationByTargetString abts = abtsProvider.getABTS();
        if (abts != null) {
            String code = codeText.getText();
            abts.setActivateCode(code);
            try {
                s = abts.getTargetString();
            } catch (ParseException ex) {
            }
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
