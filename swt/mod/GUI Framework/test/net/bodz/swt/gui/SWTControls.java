package net.bodz.swt.gui;

import net.bodz.bas.gui.GUIException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class SWTControls extends BasicGUI {

    private Text text;

    @Override
    protected Composite createInitialView(Composite parent)
            throws GUIException, SWTException {
        Composite view = new Composite(parent, SWT.NONE);
        view.setLayout(new FormLayout());

        final Button button = new Button(view, SWT.CHECK);
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                System.out.println(e);
            }
        });
        final FormData fd_button = new FormData();
        fd_button.bottom = new FormAttachment(0, 90);
        fd_button.right = new FormAttachment(0, 230);
        fd_button.top = new FormAttachment(0, 60);
        fd_button.left = new FormAttachment(0, 40);
        button.setLayoutData(fd_button);
        button.setText("Check Button");

        text = new Text(view, SWT.BORDER);
        text.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                System.out.println("DS>> " + e);
            }

            @Override
            public void widgetSelected(SelectionEvent e) {
                System.out.println(e);
            }
        });
        text.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                System.out.println(e);
            }
        });
        text.addVerifyListener(new VerifyListener() {
            public void verifyText(final VerifyEvent e) {
                System.out.println(e);
            }
        });
        final FormData fd_text = new FormData();
        fd_text.bottom = new FormAttachment(0, 180);
        fd_text.right = new FormAttachment(0, 265);
        fd_text.top = new FormAttachment(0, 125);
        fd_text.left = new FormAttachment(button, 0, SWT.LEFT);
        text.setLayoutData(fd_text);
        return view;
    }

    public static void main(String[] args) throws Throwable {
        new SWTControls().run(args);
    }

}
