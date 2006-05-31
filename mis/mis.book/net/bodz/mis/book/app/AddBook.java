package net.bodz.mis.book.app;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class AddBook {

    protected Shell shell;

    public static void main(String[] args) {
        try {
            AddBook window = new AddBook();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open() {
        final Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    protected void createContents() {
        shell = new Shell();
        shell.setLayout(new FormLayout());
        shell.setSize(522, 390);
        shell.setText("SWT Application");

        final Composite composite = new Composite(shell, SWT.NONE);
        final FormData formData = new FormData();
        formData.bottom = new FormAttachment(0, 140);
        formData.right = new FormAttachment(0, 210);
        formData.top = new FormAttachment(0, 45);
        formData.left = new FormAttachment(0, 35);
        composite.setLayoutData(formData);
        composite.setLayout(new FormLayout());
    }

}
