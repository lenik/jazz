package net.bodz.swt.c.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.junit.Test;

import net.bodz.swt.c.composite.Switcher;
import net.bodz.swt.c.test.TestComposite;
import net.bodz.swt.c.test.WidgetTester;

public class SwitcherTest
        extends WidgetTester {

    @Test
    public void test1()
            throws Exception {
        body.setLayout(new GridLayout(1, false));

        final Button check = new Button(body, SWT.CHECK);
        final Switcher switcher = new Switcher(body, SWT.BORDER, body);
        switcher.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));

        Composite trueHolder = switcher.get(true);
        // Composite falseHolder = switcher.get(false);
        TestComposite c1 = new TestComposite(trueHolder, SWT.NONE);
        // TestComposite c2 = new TestComposite(falseHolder, SWT.NONE);
        c1.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
        // c2.setBackground(app.display.getSystemColor(SWT.COLOR_BLUE));

        check.setText("&Visible");
        check.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean visible = check.getSelection();
                switcher.set(visible);
            }
        });
    }

}
