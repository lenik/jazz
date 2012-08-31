package net.bodz.swt.widgets.util;

import org.eclipse.swt.SWT;
import org.junit.Test;

import net.bodz.swt.c.test.TestComposite;
import net.bodz.swt.c.test.WidgetTester;

public class TestCompositeTest
        extends WidgetTester {

    @Test
    public void test()
            throws Exception {
        new TestComposite(body, SWT.NONE);
    }

}
