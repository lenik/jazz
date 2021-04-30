package net.bodz.pkg.sis.c;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import net.bodz.bas.c.system.SystemProperties;
import net.bodz.bas.ui.err.UiValidationException;
import net.bodz.pkg.sis.ISisComponent;
import net.bodz.pkg.sis.ISisInstallProfile;
import net.bodz.pkg.sisi.page.ConfigPage;
import net.bodz.swt.c.control.Controls;

public class ChooseInstallProfile
        extends ConfigComponent {

    private static final long serialVersionUID = 1L;

    public ChooseInstallProfile(ISisComponent parent) {
        super(parent, null);
    }

    @Override
    public ConfigPage createConfigPage() {
        return new ChooseInstallProfilePage(this);
    }

}

class ChooseInstallProfilePage
        extends ConfigPage {

    private List<ISisInstallProfile> profiles;

    private int selectedIndex = -1;

    public ChooseInstallProfilePage(ISisComponent owner) {
        super(owner);
        profiles = new ArrayList<>(getProject().getInstallProfiles());
        if (SystemProperties.isDevelopMode())
            selectedIndex = 0;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return nls.tr("Choose Install Type: ");
    }

    @Override
    protected void createContents(Composite parent) {
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        parent.setLayout(gridLayout);

        SelectionAdapter selector = new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Button button = (Button) e.getSource();
                Object data = button.getData();
                assert data != null;
                selectedIndex = (Integer) data;
            }
        };
        for (int index = 0; index < profiles.size(); index++) {
            ISisInstallProfile profile = profiles.get(index);

            final Button profileButton = new Button(parent, SWT.RADIO);
            profileButton.setText(profile.getLabel().toString());
            profileButton.setData(index);
            profileButton.addSelectionListener(selector);
            Controls.setFontStyle(profileButton, SWT.BOLD);
            Controls.setFontHeight(profileButton, 12);
            if (selectedIndex == index)
                profileButton.setSelection(true);

            final Label descriptionLabel = new Label(parent, SWT.NONE);
            descriptionLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true));
            descriptionLabel.setText(profile.getDescription().toString());
            Controls.setFontHeight(descriptionLabel, 12);
        }
    }

    @Override
    public void validate()
            throws UiValidationException {
        if (selectedIndex == -1)
            throw new UiValidationException(nls.tr("Scheme isn\'t selected"));
        ISisInstallProfile profile = profiles.get(selectedIndex);
        getProject().setInstallProfile(profile);
    }

    @Override
    public String toString() {
        return nls.tr("selected profile ") + selectedIndex;
    }

}
