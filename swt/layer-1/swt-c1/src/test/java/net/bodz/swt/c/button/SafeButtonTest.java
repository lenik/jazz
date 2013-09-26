package net.bodz.swt.c.button;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

public class SafeButtonTest {

    static Random rand = new Random();
    static int seq = 0;

    @Test
    public void test()
            throws Exception {
        Shell shell = new Shell();
        shell.setLayout(new FillLayout());
        SafeButton button = new SafeButton(shell, SWT.NONE);
        button.getButton().setText("Test");
        button.addAsyncSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int id = ++seq; // rand.nextInt();
                System.out.println("  " + id + " start");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("  " + id + " end");
            }
        });

        Display display = Display.getDefault();
        shell.open();
        shell.setSize(100, 80);
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

}
