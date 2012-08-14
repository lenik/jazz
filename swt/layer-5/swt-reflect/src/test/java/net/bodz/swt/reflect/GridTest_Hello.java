package net.bodz.swt.reflect;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.junit.Test;

import net.bodz.swt.c.resources.SWTResources;
import net.bodz.swt.c.test.ControlTestApp;

public class GridTest_Hello
        extends ControlTestApp {

    private Text text_2;
    private Text text;

    @Override
    public void run() {
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        holder.setLayout(gridLayout);

        final Label label = new Label(holder, SWT.NONE);
        label.setImage(SWTResources.getImageRes("/icons/full/obj16/genericvariable_obj.gif"));

        final Label label_1 = new Label(holder, SWT.NONE);
        label_1.setText("Label");

        text = new Text(holder, SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        final Label label_2 = new Label(holder, SWT.NONE);
        label_2.setImage(SWTResources.getImageRes("/icons/full/obj16/unknown_obj.gif"));

        final Label label_3 = new Label(holder, SWT.NONE);
        label_3.setText("Label");

        final Button button = new Button(holder, SWT.CHECK);
        button.setText("Check Button");

        final Label label_4 = new Label(holder, SWT.NONE);
        label_4.setText("lab4");

        final Label label_5 = new Label(holder, SWT.NONE);
        label_5.setText("Label");

        final Composite composite = new Composite(holder, SWT.NONE);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        final GridLayout gridLayout_1 = new GridLayout();
        gridLayout_1.numColumns = 3;
        composite.setLayout(gridLayout_1);

        final Label label_6 = new Label(composite, SWT.NONE);
        label_6.setText("Label");

        final Label label_1_1 = new Label(composite, SWT.NONE);
        label_1_1.setText("Label");

        text_2 = new Text(composite, SWT.BORDER);
        final GridData gd_text_2 = new GridData(SWT.FILL, SWT.CENTER, true, false);
        text_2.setLayoutData(gd_text_2);

        final Label label_2_1 = new Label(composite, SWT.NONE);
        label_2_1.setText("Label");

        final Label label_3_1 = new Label(composite, SWT.NONE);
        label_3_1.setText("Label");

        final Button button_1 = new Button(composite, SWT.CHECK);
        button_1.setText("Check Button");
    }

    @Test
    public void test1()
            throws Throwable {
        run();
    }

}
