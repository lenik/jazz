package net.bodz.dist.pro.util;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.dist.pro.util.ActivatePageTest;
import net.bodz.swt.adapters.ControlAdapters;
import net.bodz.swt.gui.pfl.PageComposite;

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
public class ActivatePage extends PageComposite {

    private ActivationByTargetString abt;

    private Text                     hostidText;
    private Text                     codeText;

    public ActivatePage(Composite parent, int style, ActivationByTargetString abt) {
        super(parent, style);
        if (abt == null)
            throw new NullPointerException("abt");
        this.abt = abt;
        createContents(parent, style);
    }

    void createContents(Composite parent, int style) {
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        setLayout(gridLayout);

        final Label pleaseEnterRegistrationLabel = new Label(this, SWT.NONE);
        final GridData gd_pleaseEnterRegistrationLabel = new GridData(SWT.LEFT, SWT.CENTER, false,
                false, 2, 1);
        pleaseEnterRegistrationLabel.setLayoutData(gd_pleaseEnterRegistrationLabel);
        pleaseEnterRegistrationLabel.setText("Please activate this software: ");

        final Label hostIdLabel = new Label(this, SWT.NONE);
        final GridData gd_hostIdLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false);
        hostIdLabel.setLayoutData(gd_hostIdLabel);
        hostIdLabel.setText("Host ID: ");

        hostidText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
        final GridData gd_hostidText = new GridData(SWT.FILL, SWT.CENTER, true, false);
        hostidText.setLayoutData(gd_hostidText);
        hostidText.setText(abt.getHostId());

        final Label codeLabel = new Label(this, SWT.NONE);
        final GridData gd_codeLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false);
        codeLabel.setLayoutData(gd_codeLabel);
        codeLabel.setText("Activation Code: ");

        codeText = new Text(this, SWT.BORDER);
        final GridData gd_codeText = new GridData(SWT.FILL, SWT.CENTER, true, false);
        codeText.setLayoutData(gd_codeText);
        codeText.setFocus();
        codeText.addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                getDisplay().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        firePageStateChange();
                    }
                });
            }
        });

        final Label label = new Label(this, SWT.HORIZONTAL | SWT.SEPARATOR);
        label.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, true, 2, 1));
        label.setText("Label");

        final Label label1 = new Label(this, SWT.WRAP);
        final GridData gd_label1 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 2, 1);
        label1.setLayoutData(gd_label1);
        label1.setText("Have encounter any problem?");

        String website = abt.getWebsite();
        final Link link = new Link(this, SWT.NONE);
        final GridData gd_label2 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 2, 1);
        link.setLayoutData(gd_label2);
        link.setText(String.format(
                "Access our <a href=\"%s\">website</a> to get the activation code.", website));
        if (website != null)
            link.setToolTipText(website);
        ControlAdapters.browseLink(link);
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return "Product Activation";
    }

    @Override
    protected Object getInitialState() {
        return "cancel";
    }

    @Override
    public Object exitState() {
        String code = codeText.getText();
        abt.setActivateCode(code);
        try {
            String s = abt.getTargetString();
            abt.save();
            return s;
        } catch (ParseException ex) {
            return "cancel";
        }
    }

}
