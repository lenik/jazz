package net.bodz.xfo.browser.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

public class CategoryList extends Composite {

    private final Label label;
    private final List  list;

    public CategoryList(Composite parent, int style) {
        super(parent, style);

        GridLayout layout = new GridLayout();
        setLayout(layout);

        label = new Label(this, SWT.NONE);
        label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        list = new List(this, SWT.NONE);
        list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }

    public String getText() {
        return label.getText();
    }

    public void setText(String string) {
        label.setText(string);
    }

}
