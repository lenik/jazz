package net.bodz.swt.gui;

import net.bodz.bas.gui.GUIException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import com.swtdesigner.SWTResourceManager;

public class GridTest_Hello extends BasicGUI {

    private Text text_2;
    private Text text;

    @Override
    protected Composite createInitialView(Composite parent)
            throws GUIException, SWTException {
        Composite view = new Composite(parent, SWT.NONE);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        view.setLayout(gridLayout);

        final Label label = new Label(view, SWT.NONE);
        label.setImage(SWTResourceManager.getImage(GridTest_Hello.class,
                "/icons/full/obj16/genericvariable_obj.gif"));

        final Label label_1 = new Label(view, SWT.NONE);
        label_1.setText("Label");

        text = new Text(view, SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        final Label label_2 = new Label(view, SWT.NONE);
        label_2.setImage(SWTResourceManager.getImage(GridTest_Hello.class,
                "/icons/full/obj16/unknown_obj.gif"));

        final Label label_3 = new Label(view, SWT.NONE);
        label_3.setText("Label");

        final Button button = new Button(view, SWT.CHECK);
        button.setText("Check Button");

        final Label label_4 = new Label(view, SWT.NONE);
        label_4.setText("lab4");

        final Label label_5 = new Label(view, SWT.NONE);
        label_5.setText("Label");

        final Composite composite = new Composite(view, SWT.NONE);
        composite
                .setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        final GridLayout gridLayout_1 = new GridLayout();
        gridLayout_1.numColumns = 3;
        composite.setLayout(gridLayout_1);

        final Label label_6 = new Label(composite, SWT.NONE);
        label_6.setText("Label");

        final Label label_1_1 = new Label(composite, SWT.NONE);
        label_1_1.setText("Label");

        text_2 = new Text(composite, SWT.BORDER);
        final GridData gd_text_2 = new GridData(SWT.FILL, SWT.CENTER, true,
                false);
        text_2.setLayoutData(gd_text_2);

        final Label label_2_1 = new Label(composite, SWT.NONE);
        label_2_1.setText("Label");

        final Label label_3_1 = new Label(composite, SWT.NONE);
        label_3_1.setText("Label");

        final Button button_1 = new Button(composite, SWT.CHECK);
        button_1.setText("Check Button");
        return view;
    }

    public static void main(String[] args) throws Throwable {
        new GridTest_Hello().run(args);
    }

}
