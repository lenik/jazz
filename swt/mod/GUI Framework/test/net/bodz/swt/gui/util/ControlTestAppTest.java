package net.bodz.swt.gui.util;

import net.bodz.bas.lang.Proc1;
import net.bodz.swt.controls.helper.TestComposite;

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
