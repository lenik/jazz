package net.bodz.swt.c3.control;

import net.bodz.swt.c3.test.ControlTestApp;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @see ControlTestApp
 */
public abstract class ControlTest {

    Display display;
    Shell shell;

    public synchronized void run() {
        display = Display.getDefault();
        shell = createShell();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    protected Shell createShell() {
        Shell shell = new Shell();
        shell.setSize(500, 375);
        String name = getClass().getName();
        shell.setText(name);
        shell.setLayout(new FillLayout());
        try {
            createContents(shell);
        } catch (Exception e) {
            throw new Error(e);
        }
        return shell;
    }

    protected abstract void createContents(Shell shell)
            throws Exception;

    protected Color color(int r, int g, int b) {
        return new Color(display, r, g, b);
    }

}
