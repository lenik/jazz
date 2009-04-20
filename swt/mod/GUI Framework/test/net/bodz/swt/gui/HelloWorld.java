package net.bodz.swt.gui;

import net.bodz.bas.ui.a.Color;
import net.bodz.bas.ui.a.Font;
import net.bodz.bas.ui.a.Visible;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.junit.Test;

public class HelloWorld extends BasicGUI {

    @Visible(false)
    String        greeting = "Hello"; //$NON-NLS-1$

    @Font(name = "Arial", height = 20)
    String        name     = "Caynoh"; //$NON-NLS-1$

    @Color("red")
    int           age      = 13;

    private Text  greetingText;
    private Text  nameText;
    private Text  ageText;
    private Label helloLabel;

    @Override
    protected void createInitialView(Composite comp) {
        comp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDown(final MouseEvent e) {
                System.out.println(e);
            }
        });
        comp.setCapture(true);
        comp.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                System.out.println(e);
            }

            @Override
            public void keyReleased(final KeyEvent e) {
                System.out.println(e);
            }
        });
        comp.setLayout(new FormLayout());

        final Label greetingLabel = new Label(comp, SWT.NONE);
        final FormData fd_greetingLabel = new FormData();
        fd_greetingLabel.bottom = new FormAttachment(0, 25);
        fd_greetingLabel.right = new FormAttachment(0, 95);
        fd_greetingLabel.top = new FormAttachment(0, 5);
        greetingLabel.setLayoutData(fd_greetingLabel);
        greetingLabel.setText("Greeting: "); //$NON-NLS-1$

        greetingText = new Text(comp, SWT.BORDER);
        greetingText.addVerifyListener(new VerifyListener() {
            public void verifyText(final VerifyEvent e) {
                greeting = greetingText.getText();
            }
        });
        greetingText.setText(greeting);
        final FormData fd_greetingText = new FormData();
        fd_greetingText.bottom = new FormAttachment(greetingLabel, 0,
                SWT.BOTTOM);
        fd_greetingText.right = new FormAttachment(100, -5);
        fd_greetingText.top = new FormAttachment(greetingLabel, 0, SWT.TOP);
        fd_greetingText.left = new FormAttachment(greetingLabel, 5, SWT.RIGHT);
        greetingText.setLayoutData(fd_greetingText);

        final Label nameLabel = new Label(comp, SWT.NONE);
        final FormData fd_nameLabel = new FormData();
        fd_nameLabel.bottom = new FormAttachment(0, 50);
        fd_nameLabel.right = new FormAttachment(greetingLabel, 0, SWT.RIGHT);
        fd_nameLabel.top = new FormAttachment(greetingLabel, 5, SWT.BOTTOM);
        fd_nameLabel.left = new FormAttachment(greetingLabel, 0, SWT.LEFT);
        nameLabel.setLayoutData(fd_nameLabel);
        nameLabel.setText("Name: "); //$NON-NLS-1$

        final Label ageLabel = new Label(comp, SWT.NONE);
        final FormData fd_ageLabel = new FormData();
        fd_ageLabel.bottom = new FormAttachment(0, 75);
        fd_ageLabel.right = new FormAttachment(nameLabel, 0, SWT.RIGHT);
        fd_ageLabel.top = new FormAttachment(nameLabel, 5, SWT.BOTTOM);
        fd_ageLabel.left = new FormAttachment(nameLabel, 0, SWT.LEFT);
        ageLabel.setLayoutData(fd_ageLabel);
        ageLabel.setText("Age: "); //$NON-NLS-1$

        nameText = new Text(comp, SWT.BORDER);
        nameText.addVerifyListener(new VerifyListener() {
            public void verifyText(final VerifyEvent e) {
                name = nameText.getText();
            }
        });
        nameText.setText(name);
        final FormData fd_nameText = new FormData();
        fd_nameText.bottom = new FormAttachment(nameLabel, 0, SWT.BOTTOM);
        fd_nameText.right = new FormAttachment(greetingText, 0, SWT.RIGHT);
        fd_nameText.top = new FormAttachment(greetingText, 5, SWT.BOTTOM);
        fd_nameText.left = new FormAttachment(nameLabel, 5, SWT.RIGHT);
        nameText.setLayoutData(fd_nameText);

        ageText = new Text(comp, SWT.BORDER);
        ageText.addVerifyListener(new VerifyListener() {
            public void verifyText(final VerifyEvent e) {
                try {
                    age = Integer.parseInt(ageText.getText());
                } catch (NumberFormatException err) {
                    e.doit = false;
                }
            }
        });
        ageText.setText("" + age); //$NON-NLS-1$
        final FormData fd_ageText = new FormData();
        fd_ageText.bottom = new FormAttachment(ageLabel, 0, SWT.BOTTOM);
        fd_ageText.right = new FormAttachment(nameText, 0, SWT.RIGHT);
        fd_ageText.top = new FormAttachment(nameText, 5, SWT.BOTTOM);
        fd_ageText.left = new FormAttachment(ageLabel, 5, SWT.RIGHT);
        ageText.setLayoutData(fd_ageText);

        final Button sayButton = new Button(comp, SWT.NONE);
        sayButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                String s = greeting + " " + name + ", you are " + age //$NON-NLS-1$ //$NON-NLS-2$
                        + " years old!"; //$NON-NLS-1$
                helloLabel.setText(s);
            }
        });
        final FormData fd_sayButton = new FormData();
        fd_sayButton.bottom = new FormAttachment(0, 105);
        fd_sayButton.right = new FormAttachment(ageLabel, 0, SWT.RIGHT);
        fd_sayButton.top = new FormAttachment(ageLabel, 5, SWT.BOTTOM);
        fd_sayButton.left = new FormAttachment(ageLabel, 0, SWT.LEFT);
        sayButton.setLayoutData(fd_sayButton);
        sayButton.setText("&Say: "); //$NON-NLS-1$

        helloLabel = new Label(comp, SWT.WRAP);
        helloLabel.setFont(SWTResources.getFont("Tahoma", 12, SWT.NONE)); //$NON-NLS-1$
        final FormData fd_helloLabel = new FormData();
        fd_helloLabel.bottom = new FormAttachment(100, -5);
        fd_helloLabel.right = new FormAttachment(ageText, 0, SWT.RIGHT);
        fd_helloLabel.top = new FormAttachment(ageText, 5, SWT.BOTTOM);
        fd_helloLabel.left = new FormAttachment(sayButton, 5, SWT.RIGHT);
        helloLabel.setLayoutData(fd_helloLabel);
        helloLabel.setText("hello!"); //$NON-NLS-1$

        // Scrollable scroll = new Scrollable(parent, SWT.BORDER);
        Point cs = comp.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        System.out.println(cs);
        comp.setSize(cs);

        Label greetingIcon;
        greetingIcon = new Label(comp, SWT.NONE);
        fd_greetingLabel.left = new FormAttachment(greetingIcon, 5, SWT.RIGHT);
        greetingIcon.setImage(SWTResources
                .getImageRes("/icons/full/obj16/read_obj.gif")); //$NON-NLS-1$
        final FormData fd_greetingIcon = new FormData();
        fd_greetingIcon.right = new FormAttachment(0, 25);
        fd_greetingIcon.bottom = new FormAttachment(greetingLabel, 0,
                SWT.BOTTOM);
        fd_greetingIcon.top = new FormAttachment(greetingLabel, 0, SWT.TOP);
        fd_greetingIcon.left = new FormAttachment(0, 5);
        greetingIcon.setLayoutData(fd_greetingIcon);
    }

    @Test
    public void test() throws Throwable {
        run();
    }

    @Override
    protected void checkHangOns() {
    }

}
