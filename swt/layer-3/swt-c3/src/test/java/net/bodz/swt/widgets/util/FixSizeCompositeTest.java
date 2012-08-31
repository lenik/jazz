package net.bodz.swt.widgets.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.junit.Test;

import net.bodz.swt.c.composite.FixSizeComposite;
import net.bodz.swt.c.test.TestComposite;
import net.bodz.swt.c.test.WidgetTester;

public class FixSizeCompositeTest
        extends WidgetTester {

    @Test
    public void test() {
        final FixSizeComposite filledFixSize = new FixSizeComposite(body, SWT.BORDER);
        filledFixSize.setLayout(new FillLayout());

        new TestComposite(filledFixSize, SWT.NONE);

        addToolButton("Fix", new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                filledFixSize.setFixedSize(100, 100);
                autoFit();
            }
        });
        addToolButton("Free", new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                filledFixSize.unsetFixedSize();
                autoFit();
            }
        });
    }

}
