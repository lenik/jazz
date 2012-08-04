package net.bodz.swt.c.text;

import org.eclipse.swt.widgets.Text;

public class TextAdapters {

    public static void autoSelect(final Text text) {
        text.addMouseListener(SelectAllWhenCtrlDownMouseAdapter.getInstance());
        text.addFocusListener(SelectAllFocusAdapter.getInstance());
    }

}
