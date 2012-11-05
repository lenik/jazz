package net.bodz.swt.c3.list;

import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.swt.c3.dialog.SwtDialogs;

public class ListEditor
        extends AbstractListEditor<String> {

    private IUserDialogs userDialogs;

    public ListEditor(Composite parent, int style) {
        super(parent, style);
        userDialogs = new SwtDialogs(parent.getShell());
    }

    @Override
    protected String createObject() {
        return userDialogs.prompt("Enter new item: ");
    }

}
