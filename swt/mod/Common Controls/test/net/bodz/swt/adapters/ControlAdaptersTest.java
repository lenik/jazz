package net.bodz.swt.adapters;

import net.bodz.swt.gui.util.SWTTest;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

public class ControlAdaptersTest {

    public void testMoving() {
        SWTTest test = new SWTTest();
        test.parent.setLayout(null);

        Button button = new Button(test.parent, SWT.NONE);
        button.setBounds(10, 10, 50, 20);

        ControlAdapters.makeMoveable(button, SWT.ALT);

        test.run();
    }

    public void testPanning() {

    }

    public void testWheelPanning() {

    }

    public static void main(String[] args) {
        ControlAdaptersTest test = new ControlAdaptersTest();
        test.testMoving();
    }

}
