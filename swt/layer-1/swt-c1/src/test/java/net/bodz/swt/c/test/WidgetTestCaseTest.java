package net.bodz.swt.c.test;

import org.eclipse.swt.widgets.Button;
import org.junit.Test;

public class WidgetTestCaseTest
        extends WidgetTester {

    @Test
    public void test1() {
        new TestComposite(body, 0);
    }

    @Test
    public void test2() {
        Button button = new Button(body, 0);
        button.setText("Hello, World!");
    }

}
