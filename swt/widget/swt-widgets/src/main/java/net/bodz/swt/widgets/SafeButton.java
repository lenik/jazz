package net.bodz.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * @test {@link SafeButtonTest}
 */
public class SafeButton
        extends Composite {

    static boolean DEBUG = false;

    private Display display;
    private Button button;

    public SafeButton(Composite parent, int style) {
        super(parent, style);
        this.display = parent.getDisplay();
        this.setLayout(new FillLayout());
        button = new Button(this, SWT.NONE);
    }

    public Button getButton() {
        return button;
    }

    public void addAsyncSelectionListener(final SelectionListener listener) {
        button.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (SafeButton.DEBUG)
                    System.out.println("S enter"); //$NON-NLS-1$
                synchronized (button) {
                    final boolean orig = button.isEnabled();
                    if (orig == false)
                        return;
                    button.setEnabled(false);
                    new Thread() {
                        @Override
                        public void run() {
                            listener.widgetSelected(e);
                            synchronized (button) {
                                display.syncExec(new Runnable() {
                                    @Override
                                    public void run() {
                                        button.setEnabled(orig);
                                    }
                                });
                            }
                        }
                    }.start();
                }
                if (DEBUG)
                    System.out.println("S leave"); //$NON-NLS-1$
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // the similar...
            };
        });
    }

}
