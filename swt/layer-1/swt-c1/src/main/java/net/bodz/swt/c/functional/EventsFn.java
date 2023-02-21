package net.bodz.swt.c.functional;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;

public class EventsFn {

    public static void addSelectionListener(Button button, SelectionFunc fn) {
        button.addSelectionListener(fn);
    }

    public static void addSelectionListener(Combo button, SelectionFunc fn) {
        button.addSelectionListener(fn);
    }

}
