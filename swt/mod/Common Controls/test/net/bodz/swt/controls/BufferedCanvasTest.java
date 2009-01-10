package net.bodz.swt.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public class BufferedCanvasTest {

    public static void main(String[] args) {
        new ControlTest() {

            @Override
            protected void createContents(Shell shell) throws Exception {
                new BufferedCanvas(shell, SWT.NONE);
            }

        }.run();
    }

}
