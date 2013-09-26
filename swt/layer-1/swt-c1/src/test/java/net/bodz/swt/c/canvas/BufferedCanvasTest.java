package net.bodz.swt.c.canvas;

import org.eclipse.swt.SWT;
import org.junit.Test;

import net.bodz.swt.c.test.WidgetTester;

public class BufferedCanvasTest
        extends WidgetTester {

    @Test
    public void test1() {
        new BufferedCanvas(shell, SWT.NONE);
    }

}
