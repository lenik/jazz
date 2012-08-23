package net.bodz.swt.c3.canvas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import net.bodz.swt.c.canvas.BufferedCanvas;
import net.bodz.swt.c3.control.ControlTest;

public class BufferedCanvasTest {

    public static void main(String[] args) {
        new ControlTest() {

            @Override
            protected void createContents(Shell shell)
                    throws Exception {
                new BufferedCanvas(shell, SWT.NONE);
            }

        }.run();
    }

}