package net.bodz.swt.adapters;

import net.bodz.swt.gui.util.ControlTestApp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.junit.Test;

public class ControlAdaptersTest {

    @Test
    public void testMoving() {
        ControlTestApp test = new ControlTestApp();
        test.holder.setLayout(null);

        Button button = new Button(test.holder, SWT.NONE);
        button.setBounds(10, 10, 50, 20);

        ControlAdapters.makeMoveable(button, SWT.ALT);

        test.run();
    }

    @Test
    public void testPanning() {
        ControlTestApp test = new ControlTestApp();
        test.holder.setLayout(null);

        Button button = new Button(test.holder, SWT.NONE);
        button.setBounds(10, 10, 50, 20);

        // ...
        test.run();

    }

    @Test
    public void testWheelPanning() {

    }

}
