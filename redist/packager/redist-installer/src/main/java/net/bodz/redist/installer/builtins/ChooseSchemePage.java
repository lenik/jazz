package net.bodz.redist.installer.builtins;

import static net.bodz.redist.installer.nls.PackNLS.PackNLS;

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
import net.bodz.bas.gui.err.GUIValidationException;
import net.bodz.redist.installer.ConfigPage;
import net.bodz.redist.installer.IComponent;
import net.bodz.redist.installer.ISession;
import net.bodz.redist.installer.Scheme;
import net.bodz.swt.c.control.Controls;

/**
 * @test {@link ChooseSchemePageTest}
 */
public class ChooseSchemePage
        extends ConfigPage {

    private Scheme[] schemes;

    private int selectedIndex = -1;

    public ChooseSchemePage(IComponent owner, ISession session) {
        super(owner, session);
        schemes = session.getProject().getSchemes();
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
        return PackNLS.getString("ChooseSchemePage.title");
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
        for (int i = 0; i < schemes.length; i++) {
            Scheme scheme = schemes[i];
            String caption = scheme.getCaption();
            String description = scheme.getDescription();

            final Button schemeButton = new Button(parent, SWT.RADIO);
            schemeButton.setText(caption);
            schemeButton.setData(i);
            schemeButton.addSelectionListener(selector);
            Controls.setFontStyle(schemeButton, SWT.BOLD);
            Controls.setFontHeight(schemeButton, 12);
            if (selectedIndex == i)
                schemeButton.setSelection(true);

            final Label label = new Label(parent, SWT.NONE);
            label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true));
            label.setText(description);
            Controls.setFontHeight(label, 12);
        }
    }

    @Override
    public void validate()
            throws GUIValidationException {
        if (selectedIndex == -1)
            throw new GUIValidationException(PackNLS.getString("ChooseSchemePage.notSelected"));
        Scheme scheme = schemes[selectedIndex];
        session.setScheme(scheme);
    }

    @Override
    public String toString() {
        return PackNLS.getString("ChooseSchemePage.selected") + selectedIndex;
    }

}
