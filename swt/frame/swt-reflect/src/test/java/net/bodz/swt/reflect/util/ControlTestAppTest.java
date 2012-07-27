package net.bodz.swt.reflect.util;

import net.bodz.bas.mode._fn.Proc1;
import net.bodz.swt.widgets.test.ControlTestApp;
import net.bodz.swt.widgets.util.TestComposite;

import org.eclipse.swt.widgets.Button;

public class ControlTestAppTest {

    public static void main(String[] args) {
        ControlTestApp.test(TestComposite.class);
        ControlTestApp.test("Button", Button.class, new Proc1<Button>() {
            @Override
            public void exec(Button a) {
                a.setText("Hello, World!");
            }
        });
    }

}
