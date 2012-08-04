package net.bodz.swt.c.text;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Text;

public class SelectAllWhenCtrlDownMouseAdapter
        extends MouseAdapter {

    @Override
    public void mouseDown(MouseEvent e) {
        if ((e.stateMask & SWT.CTRL) != 0)
            ((Text) e.widget).selectAll();
    }

    static final SelectAllWhenCtrlDownMouseAdapter instance = new SelectAllWhenCtrlDownMouseAdapter();

    public static SelectAllWhenCtrlDownMouseAdapter getInstance() {
        return instance;
    }

}
