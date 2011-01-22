package net.bodz.swt.widgets;

import net.bodz.swt.gui.util.ControlTestApp;
import net.bodz.swt.widgets.RolloverButton;
import net.bodz.swt.widgets.RolloverStyle;
import net.bodz.swt.widgets.util.Syscolors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.junit.Test;

public class RolloverButtonTest {

    static class SelectionDebug implements SelectionListener {

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
    public void test1() throws Exception {
        ControlTestApp app = new ControlTestApp();
        Syscolors ct = new Syscolors(app.display);
        RolloverStyle normal = new RolloverStyle(ct.black, ct.white);
        RolloverStyle hover = new RolloverStyle(ct.yellow, ct.red);
        RolloverStyle active = new RolloverStyle(ct.white, ct.black);

        RolloverButton a = new RolloverButton(app.holder, SWT.NONE);
        a.setNormalStyle(normal);
        a.setHoverStyle(hover);
        a.setActiveStyle(active);
        a.addSelectionListener(new SelectionDebug());

        final RolloverButton b = new RolloverButton(app.holder, SWT.NONE);
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

        app.run();
    }

}
