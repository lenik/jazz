package net.bodz.swt.c.button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.junit.Test;

import net.bodz.swt.c.color.Syscolors;
import net.bodz.swt.c.test.WidgetTester;

public class RolloverButtonTest
        extends WidgetTester {

    static class SelectionDebug
            implements SelectionListener {

        @Override
        public void widgetSelected(SelectionEvent e) {
            System.out.println("Selected: " + e);
        }

        @Override
        public void widgetDefaultSelected(SelectionEvent e) {
            System.out.println("DefaultSelected: " + e);
        }

    }

    @Test
    public void test1()
            throws Exception {
        Syscolors ct = new Syscolors(display);
        RolloverStyle normal = new RolloverStyle(ct.black, ct.white);
        RolloverStyle hover = new RolloverStyle(ct.yellow, ct.red);
        RolloverStyle active = new RolloverStyle(ct.white, ct.black);

        RolloverButton a = new RolloverButton(body, SWT.NONE);
        a.setText("Button");
        a.setNormalStyle(normal);
        a.setHoverStyle(hover);
        a.setActiveStyle(active);
        a.addSelectionListener(new SelectionDebug());

        final RolloverButton b = new RolloverButton(body, SWT.NONE);
        b.setText("Check");
        b.setNormalStyle(normal);
        b.setHoverStyle(hover);
        b.setActiveStyle(active);
        b.addSelectionListener(new SelectionDebug());
        b.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                b.setActive(!b.getActive());
            }
        });
    }

}
