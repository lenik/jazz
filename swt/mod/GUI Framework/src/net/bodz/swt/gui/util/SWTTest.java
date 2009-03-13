package net.bodz.swt.gui.util;

import java.lang.reflect.Constructor;

import net.bodz.bas.lang.Proc1;
import net.bodz.bas.lang.err.ReflectException;
import net.bodz.bas.lang.util.Reflects;
import net.bodz.swt.controls.util.Shells;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import swing2swt.layout.BorderLayout;

public class SWTTest {

    public Display   display = Display.getDefault();
    public String    title   = "SWT Test";
    public Shell     shell;
    public Composite parent;

    public SWTTest() {
        setup();
    }

    protected void setup() {
        display = Display.getDefault();
        shell = new Shell();
        shell.setSize(320, 240);
        shell.setText(title);

        BorderLayout layout = new BorderLayout();
        shell.setLayout(layout);

        parent = new Composite(shell, SWT.NONE);
        FillLayout parentLayout = new FillLayout();
        parentLayout.marginWidth = 5;
        parentLayout.marginHeight = 5;
        parent.setLayout(parentLayout);
        parent.setLayoutData(BorderLayout.CENTER);

        Composite tools = new Composite(shell, SWT.NONE);
        FillLayout toolsLayout = new FillLayout();
        tools.setLayout(toolsLayout);
        tools.setLayoutData(BorderLayout.NORTH);

        Button autoFit = new Button(tools, SWT.NONE);
        autoFit.setText("Auto &Fit");
        autoFit.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Point clientAreaSize = shell.computeSize(SWT.DEFAULT,
                        SWT.DEFAULT);
                Point minSize = new Point(1, 1);
                Point maxSize = new Point(640, 480);
                Shells.fitToClientAreaSize(shell, clientAreaSize, minSize,
                        maxSize);
            }
        });
    }

    public <T extends Control> T add(Class<? extends T> controlType) {
        T control;
        Constructor<? extends T> ctor;
        try {
            ctor = Reflects.getDeclaredConstructor(controlType, //
                    Composite.class, int.class);
            control = Reflects.newInstance(ctor, parent, SWT.BORDER);
        } catch (ReflectException e) {
            try {
                ctor = Reflects.getDeclaredConstructor(controlType, //
                        Composite.class);
                control = Reflects.newInstance(ctor, parent);
            } catch (ReflectException e2) {
                throw new RuntimeException(e);
            }
        }
        return control;
    }

    public void run() {
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    public static <T extends Control> void test(Class<T> controlType) {
        test(null, controlType, null);
    }

    public static <T extends Control> void test(String title,
            Class<T> controlType, Proc1<? super T> initf) {
        SWTTest test = new SWTTest();
        T control = test.add(controlType);
        if (initf == null) {
            System.out.println("Created test control: " + control);
        } else {
            initf.exec(control);
        }
        test.run();
    }

}
