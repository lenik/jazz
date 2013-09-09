package net.bodz.swt.c.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;

import net.bodz.swt.c.composite.WindowComposite;

public class DetailMessage
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
        copyItem.setText("&Copy");
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
        debugItem.setText("&Debug");
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
