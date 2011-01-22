package net.bodz.swt.reflect.util;

import net.bodz.bas.lang.Proc1;
import net.bodz.swt.reflect.util.ControlTestApp;
import net.bodz.swt.widgets.util.TestComposite;

import org.eclipse.swt.widgets.Button;

public class ControlTestAppTest {

    public static void main(String[] args) {
        ControlTestApp.test(TestComposite.class);
        ControlTestApp.test("Button", Button.class, new Proc1<Button>() { //$NON-NLS-1$
                    @Override
                    public void exec(Button a) {
                        a.setText("Hello, World!"); //$NON-NLS-1$
                    }
                });
    }

}
