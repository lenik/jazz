package net.bodz.swt.gui.pfl;

import net.bodz.bas.a.Doc;
import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.bas.ui.a.Icon;
import net.bodz.swt.gui.ValidateException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

@Doc("Test page 1")
@Icon("icons/full/obj16/ant.gif")
public class TestPage1 extends PageComposite {

    private Text ageText;
    private Text nameText;

    public TestPage1(Composite parent, int style) {
        super(parent, style);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        setLayout(gridLayout);

        final Label nameLabel = new Label(this, SWT.NONE);
        nameLabel.setText("&Name: "); //$NON-NLS-1$

        nameText = new Text(this, SWT.BORDER);
        nameText.setTextLimit(100);
        final GridData gd_nameText = new GridData(SWT.FILL, SWT.CENTER, true, false);
        nameText.setLayoutData(gd_nameText);

        final Label ageLabel = new Label(this, SWT.NONE);
        ageLabel.setText("&Age: "); //$NON-NLS-1$

        ageText = new Text(this, SWT.BORDER);
        ageText.setTextLimit(2);
        final GridData gd_ageText = new GridData(SWT.FILL, SWT.CENTER, true, false);
        ageText.setLayoutData(gd_ageText);
        //
    }

    @Override
    public void validate() throws ValidateException {
        String agestr = ageText.getText();
        try {
            int age = Integer.parseInt(agestr);
            if (age < 0)
                throw new ValidateException(ageText, new OutOfDomainException("age", age, 0)); //$NON-NLS-1$
        } catch (NumberFormatException e) {
            throw new ValidateException(ageText, e);
        }
    }

}
