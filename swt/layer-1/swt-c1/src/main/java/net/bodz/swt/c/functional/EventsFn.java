package net.bodz.swt.c.functional;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolItem;

public class EventsFn {

    public static void addSelectionListener(Button button, SelectionFunc fn) {
        button.addSelectionListener(fn);
    }

    public static void addSelectionListener(Combo button, SelectionFunc fn) {
        button.addSelectionListener(fn);
    }

    public static void addSelectionListener(ToolItem toolItem, SelectionFunc fn) {
        toolItem.addSelectionListener(fn);
    }

    public static void addSelectionListener(Table table, SelectionFunc fn) {
        table.addSelectionListener(fn);
    }

}
