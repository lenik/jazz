package net.bodz.swt.widgets.util;

import org.eclipse.swt.SWT;
import org.junit.Test;

import net.bodz.swt.c.test.ControlTestApp;
import net.bodz.swt.c.test.TestComposite;

public class TestCompositeTest {

    @Test
    public void test()
            throws Exception {
        ControlTestApp test = new ControlTestApp();
        new TestComposite(test.holder, SWT.NONE);
        test.run();
    }

}
