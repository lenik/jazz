package net.bodz.swt.widgets;

import net.bodz.swt.gui.util.ControlTestApp;
import net.bodz.swt.widgets.Timer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.junit.Test;

public class TimerTest {

    int count;

    @Test
    public void test() throws Exception {
        ControlTestApp app = new ControlTestApp();
        app.holder.setLayout(new GridLayout(1, false));
        final Label counter = new Label(app.holder, SWT.NONE);
        counter.setText("Count: ");
        counter.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        final Timer timer = new Timer(100, false, app.display) {

            @Override
            public void run() {
                count++;
                counter.setText("Count: " + count);
            }

        };
        final Button button = new Button(app.holder, SWT.CHECK);
        button.setText("Enable");
        button.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean selection = button.getSelection();
                timer.setEnabled(selection);
            }

        });
        app.run();
    }

}
