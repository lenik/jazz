package net.bodz.swt.controls.helper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class TestComposite extends Composite {

    private Text age;
    private Text name;

    /**
     * Create the composite
     * 
     * @param parent
     * @param style
     */
    public TestComposite(Composite parent, int style) {
        super(parent, style);
        setLayout(new FormLayout());

        final Label nameLabel = new Label(this, SWT.NONE);
        final FormData fd_nameLabel = new FormData();
        fd_nameLabel.bottom = new FormAttachment(0, 65);
        fd_nameLabel.right = new FormAttachment(0, 105);
        fd_nameLabel.top = new FormAttachment(0, 40);
        fd_nameLabel.left = new FormAttachment(0, 20);
        nameLabel.setLayoutData(fd_nameLabel);
        nameLabel.setText("&Name");

        name = new Text(this, SWT.BORDER);
        final FormData fd_name = new FormData();
        fd_name.bottom = new FormAttachment(nameLabel, 0, SWT.BOTTOM);
        fd_name.right = new FormAttachment(0, 255);
        fd_name.top = new FormAttachment(nameLabel, 0, SWT.TOP);
        fd_name.left = new FormAttachment(nameLabel, 0, SWT.RIGHT);
        name.setLayoutData(fd_name);

        final Label ageLabel = new Label(this, SWT.NONE);
        final FormData fd_ageLabel = new FormData();
        fd_ageLabel.bottom = new FormAttachment(0, 95);
        fd_ageLabel.right = new FormAttachment(nameLabel, 0, SWT.RIGHT);
        fd_ageLabel.top = new FormAttachment(nameLabel, 5, SWT.BOTTOM);
        fd_ageLabel.left = new FormAttachment(nameLabel, 0, SWT.LEFT);
        ageLabel.setLayoutData(fd_ageLabel);
        ageLabel.setText("&Age");

        age = new Text(this, SWT.BORDER);
        final FormData fd_age = new FormData();
        fd_age.bottom = new FormAttachment(ageLabel, 0, SWT.BOTTOM);
        fd_age.right = new FormAttachment(name, 0, SWT.RIGHT);
        fd_age.top = new FormAttachment(nameLabel, 5, SWT.BOTTOM);
        fd_age.left = new FormAttachment(ageLabel, 0, SWT.RIGHT);
        age.setLayoutData(fd_age);

        final Button cancelButton = new Button(this, SWT.NONE);
        final FormData fd_cancelButton = new FormData();
        fd_cancelButton.left = new FormAttachment(100, -95);
        fd_cancelButton.top = new FormAttachment(100, -41);
        fd_cancelButton.bottom = new FormAttachment(100, -5);
        fd_cancelButton.right = new FormAttachment(100, -5);
        cancelButton.setLayoutData(fd_cancelButton);
        cancelButton.setText("&Cancel");

        final Button okButton = new Button(this, SWT.NONE);
        final FormData fd_okButton = new FormData();
        fd_okButton.left = new FormAttachment(100, -185);
        fd_okButton.top = new FormAttachment(100, -41);
        fd_okButton.bottom = new FormAttachment(cancelButton, 0, SWT.BOTTOM);
        fd_okButton.right = new FormAttachment(cancelButton, -5, SWT.LEFT);
        okButton.setLayoutData(fd_okButton);
        okButton.setText("&OK");
        //
    }

    @Override
    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }

}
