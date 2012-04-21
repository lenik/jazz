package net.bodz.swt.widgets;

import net.bodz.swt.util.SWTResources;
import net.bodz.swt.widgets.test.ControlTestApp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;

class DetailMessage
        extends WindowComposite {

    private Text text;

    public DetailMessage(Composite parent, int style) {
        super(parent, style);
    }

    public DetailMessage(Composite parent, int style, boolean expanded, Composite fitParent) {
        super(parent, style, expanded, fitParent);
    }

    @Override
    protected void createContents(Composite parent, int style) {
        text = new Text(parent, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
        // new FixSizeComposite(parent, SWT.BORDER, 10, 10);

        ToolItem copyItem = addToolItem(SWT.PUSH);
        copyItem.setText("&Copy"); //$NON-NLS-1$
        copyItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String s = text.getText();
                if (s == null || s.isEmpty())
                    return;
                Clipboard clipboard = new Clipboard(getDisplay());
                Object[] data = { s };
                Transfer[] dataTypes = { TextTransfer.getInstance() };
                clipboard.setContents(data, dataTypes);
            }
        });
        ToolItem debugItem = addToolItem(SWT.PUSH);
        debugItem.setText("&Debug"); //$NON-NLS-1$
        debugItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                debug();
            }
        });
    }

    void debug() {
    }

}

public class WindowCompositeTest {

    private ControlTestApp test;
    private DetailMessage detail;

    public void run() {
        test = new ControlTestApp();

        detail = new DetailMessage(test.holder, SWT.NONE) {
            @Override
            void debug() {
                int wHint = SWT.DEFAULT;
                int hHint = SWT.DEFAULT;
                Point size;
                System.out.println();
                // size = titleBar.computeSize(wHint, hHint);
                //                System.out.println("Title bar=" + size); //$NON-NLS-1$
                //
                // size = stack.computeSize(wHint, hHint);
                //                System.out.println("Stack size=" + size); //$NON-NLS-1$

                size = computeSize(wHint, hHint);
                System.out.println("Detail size=" + size); //$NON-NLS-1$

                size = getParent().computeSize(wHint, hHint);
                System.out.println("Parent size=" + size); //$NON-NLS-1$

                size = test.holder.computeSize(wHint, hHint);
                System.out.println("Test.parent=" + size); //$NON-NLS-1$

                size = test.shell.computeSize(wHint, hHint);
                System.out.println("Shell size=" + size); //$NON-NLS-1$
            }
        };
        detail.setImage(SWTResources.getImageRes("/sun/print/resources/oneside.png")); //$NON-NLS-1$
        detail.setText("Detail Test"); //$NON-NLS-1$

        detail.addDetailSwitchListener(new DetailSwitchListener() {
            @Override
            public void detailSwitch(DetailSwitchEvent e) {
                test.autoFit();
            }
        });
        test.run();
    }

    public static void main(String[] args) {
        new WindowCompositeTest().run();
    }

}
