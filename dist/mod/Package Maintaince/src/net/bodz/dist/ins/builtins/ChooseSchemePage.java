package net.bodz.dist.ins.builtins;

import net.bodz.bas.sys.SystemProperties;
import net.bodz.dist.ins.ConfigPage;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.Scheme;
import net.bodz.dist.ins.builtins.ChooseSchemePageTest;
import net.bodz.dist.ins.nls.PackNLS;
import net.bodz.swt.controls.util.Controls;
import net.bodz.swt.gui.ValidateException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @test {@link ChooseSchemePageTest}
 */
public class ChooseSchemePage extends ConfigPage {

    private ISession session;
    private Scheme[] schemes;

    private int      selectedIndex = -1;

    public ChooseSchemePage(ISession session, Composite parent, int style) {
        super(parent, style);
        this.session = session;

        if (SystemProperties.isDevelopMode())
            selectedIndex = 0;

        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        setLayout(gridLayout);

        schemes = session.getProject().getSchemes();
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

            final Button schemeButton = new Button(this, SWT.RADIO);
            schemeButton.setText(caption);
            schemeButton.setData(i);
            schemeButton.addSelectionListener(selector);
            Controls.setFontStyle(schemeButton, SWT.BOLD);
            Controls.setFontHeight(schemeButton, 12);
            if (selectedIndex == i)
                schemeButton.setSelection(true);

            final Label label = new Label(this, SWT.NONE);
            label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true));
            label.setText(description);
            Controls.setFontHeight(label, 12);
        }

    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return PackNLS.getString("ChooseSchemePage.title"); //$NON-NLS-1$
    }

    @Override
    public void validate() throws ValidateException {
        if (selectedIndex == -1)
            throw new ValidateException(PackNLS.getString("ChooseSchemePage.notSelected")); //$NON-NLS-1$
        Scheme scheme = schemes[selectedIndex];
        session.setScheme(scheme);
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    @Override
    public String toString() {
        return PackNLS.getString("ChooseSchemePage.selected") + selectedIndex; //$NON-NLS-1$
    }

}
