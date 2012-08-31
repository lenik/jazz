package net.bodz.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.junit.Test;

import net.bodz.swt.c.test.WidgetTester;
import net.bodz.swt.c3.misc.Timer;

public class TimerTest
        extends WidgetTester {

    int count;

    @Test
    public void test()
            throws Exception {
        body.setLayout(new GridLayout(1, false));
        final Label counter = new Label(body, SWT.NONE);
        counter.setText("Count: ");
        counter.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        final Timer timer = new Timer(100, false, counter) {

            @Override
            public void run() {
                count++;
                counter.setText("Count: " + count);
            }

        };
        final Button button = new Button(body, SWT.CHECK);
        button.setText("Enable");
        button.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean selection = button.getSelection();
                timer.setEnabled(selection);
            }

        });
    }

}
