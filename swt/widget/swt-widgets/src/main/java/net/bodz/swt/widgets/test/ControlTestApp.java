package net.bodz.swt.widgets.test;

import java.awt.BorderLayout;
import java.lang.reflect.Constructor;

import net.bodz.bas.mode._fn.Proc1;
import net.bodz.swt.widgets.util.Controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @test {@link ControlTestAppTest}
 */
public class ControlTestApp {

    public final Display display = Display.getDefault();
    public final String title = GUINLS.getString("ControlTestApp.title");
    public final Shell shell;
    public final Composite holder;
    private Composite tools;

    public ControlTestApp() {
        shell = new Shell();
        shell.setSize(320, 240);
        shell.setText(title);

        BorderLayout layout = new BorderLayout();
        shell.setLayout(layout);

        holder = new Composite(shell, SWT.NONE);
        FillLayout fillLayout = new FillLayout();
        fillLayout.marginWidth = 5;
        fillLayout.marginHeight = 5;
        holder.setLayout(fillLayout);
        holder.setLayoutData(BorderLayout.CENTER);

        tools = new Composite(shell, SWT.NONE);
        FillLayout toolsLayout = new FillLayout();
        tools.setLayout(toolsLayout);
        tools.setLayoutData(BorderLayout.NORTH);

        Button autoFit = new Button(tools, SWT.NONE);
        autoFit.setText(GUINLS.getString("ControlTestApp.autoFit"));
        autoFit.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                autoFit();
            }
        });
    }

    public void autoFit() {
        Controls.resizeToPreferredSize(shell);
        Controls.center(shell);
    }

    public void addToolButton(String text, SelectionListener listener) {
        Button button = new Button(tools, SWT.NONE);
        button.setText(text);
        button.addSelectionListener(listener);
    }

    public <T extends Control> T add(Class<? extends T> controlType) {
        T control;
        Constructor<? extends T> ctor;
        try {
            ctor = controlType.getDeclaredConstructor(Composite.class, int.class);
            control = ctor.newInstance(holder, SWT.BORDER);
        } catch (ReflectiveOperationException e) {
            try {
                ctor = controlType.getDeclaredConstructor(Composite.class);
                control = ctor.newInstance(holder);
            } catch (ReflectiveOperationException e2) {
                throw new RuntimeException(e);
            }
        }
        return control;
    }

    public void run() {
        shell.layout();
        Controls.center(shell);

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    public static <T extends Control> void test(Class<T> controlType) {
        test(null, controlType, null);
    }

    public static <T extends Control> void test(String title, Class<T> controlType, Proc1<? super T> initf) {
        ControlTestApp test = new ControlTestApp();
        T control = test.add(controlType);
        if (initf == null) {
            System.out.println(GUINLS.getString("ControlTestApp.echo") + control);
        } else {
            initf.exec(control);
        }
        test.run();
    }

}
