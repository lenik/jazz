package net.bodz.swt.controls.helper;

import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * @test {@link TestCompositeTest}
 */
public class TestComposite extends Composite {

    private Text age;
    private Text name;

    public TestComposite(Composite parent, int style) {
        super(parent, style);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        setLayout(gridLayout);

        final ToolBar toolBar = new ToolBar(this, SWT.FLAT);
        toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        final ToolItem helloItem = new ToolItem(toolBar, SWT.PUSH);
        helloItem.setImage(SWTResources.getImageRes("/icons/full/obj16/quickfix_warning_obj.gif"));
        helloItem.setText("Hello");

        final ToolItem sep = new ToolItem(toolBar, SWT.SEPARATOR);
        sep.setText("New item");

        final ToolItem worldItem = new ToolItem(toolBar, SWT.PUSH);
        worldItem.setImage(SWTResources.getImageRes("/icons/full/obj16/innerclass_public_obj.gif"));
        worldItem.setText("Big Big World");

        final CoolBar coolBar = new CoolBar(this, SWT.NONE);
        coolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

        final CoolItem cool1 = new CoolItem(coolBar, SWT.PUSH);
        cool1.setText("New item");

        final Button wowButton = new Button(coolBar, SWT.NONE);
        wowButton.setText("Wow");
        cool1.setControl(wowButton);

        final CoolItem cool2 = new CoolItem(coolBar, SWT.PUSH);
        cool2.setText("New item");

        final Composite composite_1 = new Composite(coolBar, SWT.NONE);
        final RowLayout rowLayout = new RowLayout();
        rowLayout.marginBottom = 0;
        rowLayout.marginRight = 0;
        rowLayout.marginTop = 0;
        rowLayout.marginLeft = 0;
        composite_1.setLayout(rowLayout);
        cool2.setControl(composite_1);

        final Button morningButton = new Button(composite_1, SWT.TOGGLE);
        morningButton.setText("Morning");

        final Button eveningButton = new Button(composite_1, SWT.TOGGLE);
        eveningButton.setText("Evening");

        final Label hr = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
        hr.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        final Label nameLabel = new Label(this, SWT.NONE);
        nameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
        nameLabel.setText("&Name"); //$NON-NLS-1$

        name = new Text(this, SWT.BORDER);
        name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

        final Label ageLabel = new Label(this, SWT.NONE);
        ageLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
        ageLabel.setText("&Age"); //$NON-NLS-1$

        age = new Text(this, SWT.BORDER);
        age.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

        final Composite composite = new Composite(this, SWT.NONE);
        composite.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false, true, 2, 1));
        final RowLayout rowLayout_1 = new RowLayout();
        rowLayout_1.pack = false;
        composite.setLayout(rowLayout_1);

        final Button okButton = new Button(composite, SWT.NONE);
        okButton.setText("&OK"); //$NON-NLS-1$

        final Button cancelButton = new Button(composite, SWT.NONE);
        cancelButton.setText("&Cancel"); //$NON-NLS-1$
        //
    }

}
