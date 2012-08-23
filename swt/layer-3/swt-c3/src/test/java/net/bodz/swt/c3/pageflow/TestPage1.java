package net.bodz.swt.c3.pageflow;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.gui.a.Icon;
import net.bodz.bas.gui.err.GUIValidationException;

/**
 * Test page 1
 */
@Icon("icons/full/obj16/ant.gif")
public class TestPage1
        extends AbstractPage {

    private Text ageText;
    private Text nameText;

    public TestPage1() {
        addMethod(new PageMethod(TestPage2.class));
    }

    @Override
    protected void createContents(Composite parent) {
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        parent.setLayout(gridLayout);

        final Label nameLabel = new Label(parent, SWT.NONE);
        nameLabel.setText("&Name: ");

        nameText = new Text(parent, SWT.BORDER);
        nameText.setTextLimit(100);
        final GridData gd_nameText = new GridData(SWT.FILL, SWT.CENTER, true, false);
        nameText.setLayoutData(gd_nameText);

        final Label ageLabel = new Label(parent, SWT.NONE);
        ageLabel.setText("&Age: ");

        ageText = new Text(parent, SWT.BORDER);
        ageText.setTextLimit(2);
        final GridData gd_ageText = new GridData(SWT.FILL, SWT.CENTER, true, false);
        ageText.setLayoutData(gd_ageText);
        //
    }

    @Override
    public void validate()
            throws GUIValidationException {
        String agestr = ageText.getText();
        try {
            int age = Integer.parseInt(agestr);
            if (age < 0)
                throw new GUIValidationException(ageText, new OutOfDomainException("age", age, 0));
        } catch (NumberFormatException e) {
            throw new GUIValidationException(ageText, e);
        }
    }

}
