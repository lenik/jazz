package net.bodz.swt.controls.helper;

import net.bodz.swt.gui.util.ControlTestApp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;

public class FixSizeCompositeTest {

    public static void main(String[] args) {
        final ControlTestApp test = new ControlTestApp();

        final FixSizeComposite comp = new FixSizeComposite(test.parent, SWT.BORDER);
        comp.setLayout(new FillLayout());
        new TestComposite(comp, SWT.NONE);

        test.addToolButton("Fix", new SelectionAdapter() { //$NON-NLS-1$
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        comp.setFixedSize(100, 100);
                        test.autoFit();
                    }
                });
        test.addToolButton("Free", new SelectionAdapter() { //$NON-NLS-1$
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        comp.unsetFixedSize();
                        test.autoFit();
                    }
                });

        test.run();
    }

}
