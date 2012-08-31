package net.bodz.swt.c.control;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.junit.Test;

import net.bodz.swt.c.test.WidgetTester;

public class ControlAdaptersTest
        extends WidgetTester {

    @Test
    public void testMoving() {
        body.setLayout(null);

        Button button = new Button(body, SWT.NONE);
        button.setBounds(10, 10, 50, 20);
        button.setText("Move Me with [ALT]!");

        ControlAdapters.makeMoveable(button, SWT.ALT);
    }

    // @Test
    public void testPanning() {
        body.setLayout(null);

        Button button = new Button(body, SWT.NONE);
        button.setBounds(10, 10, 50, 20);

        // ControlAdapters.makePannable(button);
    }

    // @Test
    public void testWheelPanning() {
    }

}
