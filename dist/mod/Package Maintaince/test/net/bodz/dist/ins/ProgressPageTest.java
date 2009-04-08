package net.bodz.dist.ins;

import net.bodz.swt.gui.util.ControlTestApp;

import org.eclipse.swt.SWT;

public class ProgressPageTest {

    public static void main(String[] args) {
        TestSession session = new TestSession();
        ControlTestApp test = new ControlTestApp();
        ProgressPage pp = new ProgressPage(session, test.parent, SWT.NONE);
        System.out.println("Created: " + pp);
        test.run();
    }

}
