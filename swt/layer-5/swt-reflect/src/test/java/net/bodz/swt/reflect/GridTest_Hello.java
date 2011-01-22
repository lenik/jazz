package net.bodz.swt.reflect;

import net.bodz.bas.ui.UIException;
import net.bodz.swt.reflect.BasicGUI;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.junit.Test;

public class GridTest_Hello extends BasicGUI {

    private Text text_2;
    private Text text;

    @Override
    protected void createInitialView(Composite comp) throws UIException {
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        comp.setLayout(gridLayout);

        final Label label = new Label(comp, SWT.NONE);
        label.setImage(SWTResources.getImageRes("/icons/full/obj16/genericvariable_obj.gif")); //$NON-NLS-1$

        final Label label_1 = new Label(comp, SWT.NONE);
        label_1.setText("Label"); //$NON-NLS-1$

        text = new Text(comp, SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        final Label label_2 = new Label(comp, SWT.NONE);
        label_2.setImage(SWTResources.getImageRes("/icons/full/obj16/unknown_obj.gif")); //$NON-NLS-1$

        final Label label_3 = new Label(comp, SWT.NONE);
        label_3.setText("Label"); //$NON-NLS-1$

        final Button button = new Button(comp, SWT.CHECK);
        button.setText("Check Button"); //$NON-NLS-1$

        final Label label_4 = new Label(comp, SWT.NONE);
        label_4.setText("lab4"); //$NON-NLS-1$

        final Label label_5 = new Label(comp, SWT.NONE);
        label_5.setText("Label"); //$NON-NLS-1$

        final Composite composite = new Composite(comp, SWT.NONE);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        final GridLayout gridLayout_1 = new GridLayout();
        gridLayout_1.numColumns = 3;
        composite.setLayout(gridLayout_1);

        final Label label_6 = new Label(composite, SWT.NONE);
        label_6.setText("Label"); //$NON-NLS-1$

        final Label label_1_1 = new Label(composite, SWT.NONE);
        label_1_1.setText("Label"); //$NON-NLS-1$

        text_2 = new Text(composite, SWT.BORDER);
        final GridData gd_text_2 = new GridData(SWT.FILL, SWT.CENTER, true, false);
        text_2.setLayoutData(gd_text_2);

        final Label label_2_1 = new Label(composite, SWT.NONE);
        label_2_1.setText("Label"); //$NON-NLS-1$

        final Label label_3_1 = new Label(composite, SWT.NONE);
        label_3_1.setText("Label"); //$NON-NLS-1$

        final Button button_1 = new Button(composite, SWT.CHECK);
        button_1.setText("Check Button"); //$NON-NLS-1$
    }

    @Override
    protected void checkHangOns() {
    }

    @Test
    public void test1() throws Throwable {
        run();
    }

}
