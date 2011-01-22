package net.bodz.swt.widgets;

import net.bodz.bas.ui.UserInterface;
import net.bodz.swt.gui.DialogUI;

import org.eclipse.swt.widgets.Composite;

public class ListEditor extends _ListEditor<String> {

    private UserInterface UI;

    public ListEditor(Composite parent, int style) {
        super(parent, style);
        UI = new DialogUI(parent.getShell());
    }

    @Override
    protected String createObject() {
        return UI.prompt("Enter new item: ");
    }

}
