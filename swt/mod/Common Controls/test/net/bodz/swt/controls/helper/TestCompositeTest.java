package net.bodz.swt.controls.helper;

import net.bodz.swt.gui.util.ControlTestApp;

import org.eclipse.swt.SWT;

public class TestCompositeTest {

    public static void main(String[] args) {
        ControlTestApp test = new ControlTestApp();
        new TestComposite(test.parent, SWT.NONE);
        test.run();
    }

}
