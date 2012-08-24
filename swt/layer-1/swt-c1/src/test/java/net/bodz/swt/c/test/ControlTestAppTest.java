package net.bodz.swt.c.test;

import org.eclipse.swt.widgets.Button;

import net.bodz.bas.lang.fn.Proc1;

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
