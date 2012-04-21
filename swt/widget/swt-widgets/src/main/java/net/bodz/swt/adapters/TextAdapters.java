package net.bodz.swt.adapters;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Text;

public class TextAdapters {

    static final MouseListener selectAllWhenCtrlDown;
    static final FocusListener selectAllWhenFocusGained;

    static {
        selectAllWhenCtrlDown = new MouseAdapter() {
            @Override
            public void mouseDown(MouseEvent e) {
                if ((e.stateMask & SWT.CTRL) != 0)
                    ((Text) e.widget).selectAll();
            }
        };
        selectAllWhenFocusGained = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                ((Text) e.widget).selectAll();
            }
        };
    }

    public static void autoSelect(final Text text) {
        text.addMouseListener(selectAllWhenCtrlDown);
        text.addFocusListener(selectAllWhenFocusGained);
    }

}
