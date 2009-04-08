package net.bodz.swt.controls.util;

import net.bodz.swt.controls.helper.FixSizeComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ControlsTest {

    Display display = Display.getDefault();

    public void testShellSize() {
        Shell shell = new Shell();
        shell.setText("");
        shell.setLayout(new FillLayout());
        new FixSizeComposite(shell, SWT.BORDER, 1, 1);

        Point size = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        System.out.println("shell/empty size = " + size);
        Controls.resizeToPreferredSize(shell);
        shell.layout();
        shell.open();
        size = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        System.out.println("shell/empty size (layout) = " + size);
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    public static void main(String[] args) {
        ControlsTest test = new ControlsTest();
        test.testShellSize();
    }

}
