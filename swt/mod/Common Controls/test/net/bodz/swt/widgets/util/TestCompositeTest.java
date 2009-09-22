package net.bodz.swt.widgets.util;

import net.bodz.swt.gui.util.ControlTestApp;
import net.bodz.swt.widgets.util.TestComposite;

import org.eclipse.swt.SWT;
import org.junit.Test;

public class TestCompositeTest {

    @Test
    public void test() throws Exception {
        ControlTestApp test = new ControlTestApp();
        new TestComposite(test.holder, SWT.NONE);
        test.run();
    }

}
