package net.bodz.swt.c.text;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Text;

public class SelectAllFocusAdapter
        extends FocusAdapter {

    @Override
    public void focusGained(FocusEvent e) {
        ((Text) e.widget).selectAll();
    }

    static final SelectAllFocusAdapter instance = new SelectAllFocusAdapter();

    public static SelectAllFocusAdapter getInstance() {
        return instance;
    }

}